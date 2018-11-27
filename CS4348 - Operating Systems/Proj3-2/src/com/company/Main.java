package com.company;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static com.company.Main.getBinaryString;
import static java.nio.charset.StandardCharsets.UTF_8;

public class Main {

    private static Scanner kb = new Scanner(System.in);
    private static Disk disk;

    public static void main(String[] args) {

        disk = new Disk(2);

        String response = "";

        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1) Display a file");
            System.out.println("2) Display the file table");
            System.out.println("3) Display the free space bitmap");
            System.out.println("4) Display a disk block");
            System.out.println("5) Copy a file from the simulation to a file on the real system");
            System.out.println("6) Copy a file from the real system to a file in the simulation");
            System.out.println("7) Delete a file");
            System.out.println("8) Exit");

            System.out.println(response + "\n");

            System.out.print(">> ");


            String input = kb.nextLine();
            String[] tokens = input.split(" ");

            switch (tokens[0]) {
                case "1":
                    response = getFileFromDisk(tokens[1]);
                    break;
                case "2":
                    response = disk.getBlock(0);
                    break;
                case "3":
                    response = disk.getBlock(1);
                    break;
                case "4":
                    response = disk.getBlock(Integer.parseInt(tokens[1]));
                    break;
                case "5":
                    response = saveFromDisk(tokens[1], ((tokens.length > 2) ? tokens[2] : ""));
                    break;
                case "6":
                    response = writeToDiskFromFile(tokens[1]);
                    break;
                case "7":
                    response = deleteFileFromDisk(tokens[1]);
                    break;
                case "8":
                    System.out.println("Exiting Program...");
                    System.exit(0);
                    break;
            }
        }

    }

    private static String writeToDiskFromFile(String fileName) {

        if (fileName.length() > 8) {
            return "\nThere was an error, file name too long!";
        }

        try {

            String text;
//            text = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
            byte bytes[] = Files.readAllBytes(Paths.get(fileName));
            System.out.println(Arrays.toString(bytes));

            StringBuilder outtxt = new StringBuilder();
            for (byte b : bytes) {
                System.out.println(b);
                outtxt.append(String.format("%8s", Integer.toBinaryString((b & 0xFF) + 0x100).substring(1)));
            }
//            System.out.println(outtxt);
            byte test[] = new BigInteger(outtxt.toString(), 2).toByteArray();
            System.out.println(Arrays.toString(test));
//            System.out.println(outtxt);

//            int fileSize = text.length() * 8;
            int fileSize = outtxt.length() / 8;
            int blockSpan = (int) Math.ceil(fileSize / 8.0 / 512.0);

            System.out.println("File: " + fileName + ", Filesize: " + fileSize + "bytes, Blocksize: " + blockSpan);

            if (fileName.length() < 8) {
                StringBuilder fileNameBuilder = new StringBuilder(fileName);
                for (int i = fileNameBuilder.length(); i < 8; i++) {
                    fileNameBuilder.insert(0, " ");
                }
                fileName = fileNameBuilder.toString();
            }

            if (blockSpan > 10) {
                return "Maximum blockspan exceeded, cannot insert file";
            }

            return disk.write(fileName, outtxt.toString(), blockSpan);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "There was an error writing to the disk";
    }

    private static String deleteFileFromDisk(String fileName) {

        if (fileName.length() > 8) {
            return "\nThere was an error, file name too long!";
        }

        return disk.delete(fileName);
    }

    private static String saveFromDisk(String fileName, String fileName2) {

        if (fileName.length() > 8) {
            return "\nThere was an error, file name too long!";
        }

        return disk.save(fileName, fileName2);
    }

    private static String getFileFromDisk(String fileName) {

        if (fileName.length() > 8) {
            return "\nThere was an error, file name too long!";
        }

        return disk.read(fileName);
    }

    static String getBinaryString(String s, Integer type) {

        if (type == 1) {
            try {
                StringBuilder binary = new StringBuilder();
                int b = Integer.parseInt(s);
                int power = 7;
                while (power > -1) {
                    int pow = (int) Math.pow(2, power);
                    if (b - pow >= 0) {
                        b -= pow;
                        power--;
                        binary.append("1");
                    } else {
                        power--;
                        binary.append("0");
                    }
                }
//                System.out.println("Converted {" + s + "} to " + binary.toString());
                return binary.toString();

            } catch (Exception ignored) {

            }

            byte[] bytes = s.getBytes(UTF_8);

            StringBuilder binary = new StringBuilder();
            for (byte b : bytes) {
                int power = 7;
                while (power > -1) {
                    int pow = (int) Math.pow(2, power);
                    if (b - pow >= 0) {
                        b -= pow;
                        power--;
                        binary.append("1");
                    } else {
                        power--;
                        binary.append("0");
                    }
                }
            }
            return binary.toString();
        } else {
            byte[] bytes = s.getBytes(UTF_8);

            StringBuilder binary = new StringBuilder();
            for (byte b : bytes) {
                int power = 7;
                while (power > -1) {
                    int pow = (int) Math.pow(2, power);
                    if (b - pow >= 0) {
                        b -= pow;
                        power--;
                        binary.append("1");
                    } else {
                        power--;
                        binary.append("0");
                    }
                }
            }
            return binary.toString();
        }
    }

    static String getStringFromBinary(String s) {

        StringBuilder out = new StringBuilder();
        StringBuilder temp = new StringBuilder();


        for (int i = 0; i < s.length(); i++) {
            temp.append(s.charAt(i));
            if (temp.length() == 8) {
                out.append(processBinaryString(temp.toString()));
                temp = new StringBuilder();
            }
        }
        return out.toString();
    }

    private static String processBinaryString(String s) {
        int charCode = 0;
        for (int i = 0; i < 8; i++) {
            if (s.charAt(i) == '1') {
                charCode += Math.pow(2, 7 - i);
            }
        }
        return String.valueOf((char) charCode);
    }

}

class Disk {

    private ArrayList<FixedSizeBitSet> diskDrive;

    private int numOfBlocks = 256;
    private int blockSize = 512 * 8;
    private int type;

    Disk(int type) {
        System.out.println("Beginning Disk Initialization...");

        this.type = type;
        this.diskDrive = new ArrayList<>(Collections.nCopies(numOfBlocks, new FixedSizeBitSet(blockSize)));
        createFileAllocationTable();
        createFreeSpaceTable();

        System.out.println("...Disk Initialization Complete\n");
    }

    private void createFileAllocationTable() {
        FixedSizeBitSet fixedSizeBitSet = new FixedSizeBitSet(blockSize);

        fixedSizeBitSet.append(1, "filetabl", "0", "1");
        fixedSizeBitSet.append(1, "freetabl", "1", "1");

        diskDrive.set(0, fixedSizeBitSet);
    }

    private void createFreeSpaceTable() {
        FixedSizeBitSet freeTableSpace = new FixedSizeBitSet(numOfBlocks);

        freeTableSpace.set(0, true);
        freeTableSpace.set(1, true);

        diskDrive.set(1, freeTableSpace);
    }

    String getBlock(int index) {
        return diskDrive.get(index).toString();
    }

    String write(String fileName, String s, int blockSpan) {
        if (type == 0) {
            return continuousAllocation(fileName, s, blockSpan);
        } else if (type == 1) {
            return chainedAllocation(fileName, s, blockSpan);
        } else if (type == 2) {
            return indexedAllocation(fileName, s, blockSpan);
        } else {
            return "There was a type error";
        }
    }

    String read(String fileName) {
        if (type == 0) {
            return readContinuous(fileName);
        } else if (type == 1) {
            return readChained(fileName);
        } else if (type == 2) {
            return readIndexed(fileName);
        } else {
            return "There was a type error";
        }
    }

    String save(String fileName, String fileName2) {
        if (type == 0) {
            return saveContinuous(fileName, fileName2);
        } else if (type == 1) {
            return saveChained(fileName, fileName2);
        } else if (type == 2) {
            return saveIndexed(fileName, fileName2);
        } else {
            return "There was a type error";
        }
    }

    String delete(String fileName) {
        if (type == 0) {
            return deleteContinuous(fileName);
        } else if (type == 1) {
            return deleteChained(fileName);
        } else if (type == 2) {
            return deleteIndexed(fileName);
        } else {
            return "There was a type error";
        }
    }

    private String readContinuous(String fileName) {
        List<AllocationRow> allocationRows = readFileAllocationTable();
//        System.out.println(allocationRows);

        int index = -1;
        int span = -1;
        for (AllocationRow row : allocationRows) {
            if (row.getFileName().equals(fileName)) {
                index = Integer.parseInt(row.getFileIndex());
                span = Integer.parseInt(row.getFileSpan());
            }
        }

        int size = (span) * (512 * 8);

        FixedSizeBitSet fixedSizeBitSet = new FixedSizeBitSet(size);

        for (int i = index; i < index + span; i++) {
            FixedSizeBitSet file = diskDrive.get(i);
            for (int j = 0; j < file.getFreeLocation(); j++) {
                fixedSizeBitSet.set(j + ((i - index) * 512 * 8), file.get(j));
            }
        }

        if (index == -1 || span == -1) {
            return "\nCould not find the file in the file system";
        }

        return "\n" + fileName + ":\n" + fixedSizeBitSet.toString();
    }

    private String deleteContinuous(String fileName) {
        List<AllocationRow> allocationRows = readFileAllocationTable();
//        System.out.println(allocationRows);

        int index = -1;
        int span = -1;
        for (AllocationRow row : allocationRows) {
            if (row.getFileName().equals(fileName)) {
                index = Integer.parseInt(row.getFileIndex());
                span = Integer.parseInt(row.getFileSpan());
            }
        }

        for (int i = index; i < index + span; i++) {
            diskDrive.get(1).set(i, false);
            diskDrive.set(i, new FixedSizeBitSet(512 * 8));
        }

        if (index == -1 || span == -1) {
            return "\nCould not find the file in the file system";
        }

        deleteFromAllocationTable(fileName);
        return "\n" + fileName + " successfully deleted from the filesystem in a continuous manner";
    }

    private String saveContinuous(String fileName, String fileName2) {
        List<AllocationRow> allocationRows = readFileAllocationTable();
//        System.out.println(allocationRows);

        int index = -1;
        int span = -1;
        for (AllocationRow row : allocationRows) {
            if (row.getFileName().equals(fileName)) {
                index = Integer.parseInt(row.getFileIndex());
                span = Integer.parseInt(row.getFileSpan());
            }
        }

        int size = 0;

        for (int i = index; i < index + span; i++) {
            FixedSizeBitSet file = diskDrive.get(i);
            size += file.getFreeLocation();
        }

        FixedSizeBitSet fixedSizeBitSet = new FixedSizeBitSet(size);

        for (int i = index; i < index + span; i++) {
            FixedSizeBitSet file = diskDrive.get(i);
            for (int j = 0; j < file.getFreeLocation(); j++) {
                fixedSizeBitSet.set(j + ((i - index) * 512 * 8), file.get(j));
            }
        }

        if (index == -1 || span == -1) {
            return "\nCould not find the file in the file system";
        }

        System.out.println("Wrote: " + fixedSizeBitSet.__toString());

        try {
            BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(((fileName2.equals("") ? fileName : fileName2))));
            bw.write(fixedSizeBitSet.toByteArray());
            bw.flush();
            bw.close();
            return "\n" + fileName + " successfully saved onto your machine in a continuous manner";
        } catch (IOException e) {
            return "\nSomething in writing went wrong";
        }


    }

    private String readChained(String fileName) {
        List<AllocationRow> allocationRows = readFileAllocationTable();
        System.out.println(allocationRows);

        int index = -1;
        int span = -1;
        for (AllocationRow row : allocationRows) {
            if (row.getFileName().equals(fileName)) {
                index = Integer.parseInt(row.getFileIndex());
                span = Integer.parseInt(row.getFileSpan());
            }
        }

        int size = (span) * (512 * 8);

        FixedSizeBitSet fixedSizeBitSet = new FixedSizeBitSet(size);


        int iteration = 0;
        while (iteration < span) {
            FixedSizeBitSet file = diskDrive.get(index);
            for (int j = 0; j < (512 * 8) - 8; j++) {
                fixedSizeBitSet.set(j + ((iteration) * 512 * 8), file.get(j));
            }
            index = Integer.parseInt(file.getFromRange(4088, 4096).__intToString());
            iteration++;
        }

        if (index == -1 || span == -1) {
            return "\nCould not find the file in the file system";
        }

        return "\n" + fileName + ":\n" + fixedSizeBitSet.toString();
    }

    private String deleteChained(String fileName) {
        List<AllocationRow> allocationRows = readFileAllocationTable();
        System.out.println(allocationRows);

        int index = -1;
        int span = -1;
        for (AllocationRow row : allocationRows) {
            if (row.getFileName().equals(fileName)) {
                index = Integer.parseInt(row.getFileIndex());
                span = Integer.parseInt(row.getFileSpan());
            }
        }

        int iteration = 0;
        while (iteration < span) {
            FixedSizeBitSet file = diskDrive.get(index);
            diskDrive.get(1).set(index, false);
            diskDrive.set(index, new FixedSizeBitSet(512 * 8));
            index = Integer.parseInt(file.getFromRange(4088, 4096).__intToString());
            System.out.println("new loc: " + index);
            iteration++;
        }

        if (index == -1 || span == -1) {
            return "\nCould not find the file in the file system";
        }

        deleteFromAllocationTable(fileName);
        return "\n" + fileName + " successfully deleted from the filesystem in a chained manner";
    }

    private String saveChained(String fileName, String fileName2) {
        List<AllocationRow> allocationRows = readFileAllocationTable();
        System.out.println(allocationRows);

        int index = -1;
        int span = -1;
        for (AllocationRow row : allocationRows) {
            if (row.getFileName().equals(fileName)) {
                index = Integer.parseInt(row.getFileIndex());
                span = Integer.parseInt(row.getFileSpan());
            }
        }

        int size = 0;
        int tmpIndex = index;
        for (int i = 0; i < span; i++) {
            FixedSizeBitSet file = diskDrive.get(tmpIndex);
            size += file.getFreeLocation();
            tmpIndex = Integer.parseInt(file.getFromRange(4088, 4096).__intToString());
        }

        FixedSizeBitSet fixedSizeBitSet = new FixedSizeBitSet(size);

        int iteration = 0;
        while (iteration < span) {
            FixedSizeBitSet file = diskDrive.get(index);
            for (int j = 0; j < ((file.getFreeLocation() == 4096) ? file.getFreeLocation() - 8 : file.getFreeLocation()); j++) {
                fixedSizeBitSet.set(j + ((iteration) * 512 * 8), file.get(j));
            }
            index = Integer.parseInt(file.getFromRange(4088, 4096).__intToString());
            iteration++;
        }

        if (index == -1 || span == -1) {
            return "\nCould not find the file in the file system";
        }

        try {
            BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(((fileName2.equals("") ? fileName : fileName2))));
            bw.write(fixedSizeBitSet.toByteArray());
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "\n" + fileName + " successfully saved onto your machine from a chained method";

    }

    private String readIndexed(String fileName) {
        List<AllocationRow> allocationRows = readFileAllocationTable();
        System.out.println(allocationRows);

        int index = -1;
        int span = -1;
        for (AllocationRow row : allocationRows) {
            if (row.getFileName().equals(fileName)) {
                index = Integer.parseInt(row.getFileIndex());
                span = Integer.parseInt(row.getFileSpan());
            }
        }

        int size = (span) * (512 * 8);

        FixedSizeBitSet fixedSizeBitSet = new FixedSizeBitSet(size);

        List<Integer> indexRows = readIndexTable(index, span);
        System.out.println(indexRows);

        int iteration = 0;
        while (iteration < span) {
            FixedSizeBitSet file = diskDrive.get(indexRows.get(iteration));
            for (int j = 0; j < (512 * 8); j++) {
                fixedSizeBitSet.set(j + ((iteration) * 512 * 8), file.get(j));
            }
            iteration++;
        }

        if (index == -1 || span == -1) {
            return "\nCould not find the file in the file system";
        }

        return "\n" + fileName + ":\n" + fixedSizeBitSet.toString();
    }

    private String deleteIndexed(String fileName) {
        List<AllocationRow> allocationRows = readFileAllocationTable();
        System.out.println(allocationRows);

        int index = -1;
        int span = -1;
        for (AllocationRow row : allocationRows) {
            if (row.getFileName().equals(fileName)) {
                index = Integer.parseInt(row.getFileIndex());
                span = Integer.parseInt(row.getFileSpan());
            }
        }

        int size = (span) * (512 * 8);

        List<Integer> indexRows = readIndexTable(index, span);
        System.out.println(indexRows);

        int iteration = 0;
        while (iteration < span) {
            diskDrive.get(1).set(indexRows.get(iteration), false);
            diskDrive.set(indexRows.get(iteration), new FixedSizeBitSet(512 * 8));
            iteration++;
        }

        if (index == -1 || span == -1) {
            return "\nCould not find the file in the file system";
        }

        diskDrive.set(index, new FixedSizeBitSet(512 * 8));
        deleteFromAllocationTable(fileName);

        return "\n" + fileName + " successfully deleted from the filesystem in a indexed manner";
    }

    private String saveIndexed(String fileName, String fileName2) {
        List<AllocationRow> allocationRows = readFileAllocationTable();
        System.out.println(allocationRows);

        int index = -1;
        int span = -1;
        for (AllocationRow row : allocationRows) {
            if (row.getFileName().equals(fileName)) {
                index = Integer.parseInt(row.getFileIndex());
                span = Integer.parseInt(row.getFileSpan());
            }
        }

//        int size = (span) * (512 * 8);
        List<Integer> indexRows = readIndexTable(index, span);

        int size = 0;
        for (int i = 0; i < span; i++) {
            FixedSizeBitSet file = diskDrive.get(indexRows.get(i));
            size += file.getFreeLocation();
        }

        FixedSizeBitSet fixedSizeBitSet = new FixedSizeBitSet(size);

        int iteration = 0;
        while (iteration < span) {
            FixedSizeBitSet file = diskDrive.get(indexRows.get(iteration));
            System.out.println("Got block @: " + indexRows.get(iteration));
            for (int j = 0; j < file.getFreeLocation(); j++) {
                fixedSizeBitSet.set(j + ((iteration) * 512 * 8), file.get(j));
            }
            iteration++;
        }

        if (index == -1 || span == -1) {
            return "\nCould not find the file in the file system";
        }

        try {
            BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(((fileName2.equals("") ? fileName : fileName2))));
            bw.write(fixedSizeBitSet.toByteArray());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "\n" + fileName + " successfully saved onto your machine from a indexed method";
    }

    private List<Integer> readIndexTable(int index, int span) {
        FixedSizeBitSet indexTable = diskDrive.get(index);
        List<Integer> spots = new ArrayList<>();

        for (int i = 0; i < span; i++) {
            spots.add(Integer.parseInt(indexTable.getFromRange(i * 8, (i + 1) * 8).__intToString()));
        }

        return spots;
    }

    private int getNumOfFreeSpace() {
        FixedSizeBitSet fixedSizeBitSet = diskDrive.get(1);

        int free = 0;
        for (int i = 0; i < fixedSizeBitSet.size(); i++) {
            if (!fixedSizeBitSet.get(i)) {
                free++;
            }
        }

        return free;
    }

//    private int getNextFreeSpace() {
//        FixedSizeBitSet fixedSizeBitSet = diskDrive.get(1);
//
//        int spot = 0;
//        for (int i = 0; i < fixedSizeBitSet.size(); i++) {
//            if (!fixedSizeBitSet.get(i)) {
//                spot = i;
//                break;
//            }
//        }
//
//        return spot;
//    }

    private boolean[] readFreeSpaceTable() {
        FixedSizeBitSet fixedSizeBitSet = diskDrive.get(1);

        boolean b[] = new boolean[256];

        for (int i = 0; i < b.length; i++) {
            b[i] = fixedSizeBitSet.get(i);
        }
        return b;
    }

    private List<AllocationRow> readFileAllocationTable() {
        FixedSizeBitSet fixedSizeBitSet = diskDrive.get(0);
        List<AllocationRow> allocationRows = new ArrayList<>();

        for (int i = 0; i < fixedSizeBitSet.getFreeLocation(); i += 80) {
            String fileName = fixedSizeBitSet.getFromRange(i, i + 64).__toString();
            String fileIndex = fixedSizeBitSet.getFromRange(i + 64, i + 72).__intToString();
            String fileSpan = fixedSizeBitSet.getFromRange(i + 72, i + 80).__intToString();

            allocationRows.add(new AllocationRow(fileName, fileIndex, fileSpan));
        }

//        System.out.println(allocationRows);
        return allocationRows;
    }

    private void deleteFromAllocationTable(String fileName) {

        FixedSizeBitSet fixedSizeBitSet = new FixedSizeBitSet(512 * 8);

        List<AllocationRow> allocationRows = readFileAllocationTable();

        for (AllocationRow row : allocationRows) {
            if (row.getFileName().equals(fileName)) {
                allocationRows.remove(row);
                break;
            }
        }

        for (AllocationRow row : allocationRows) {
            fixedSizeBitSet.append(1, row.getFileName(), row.getFileIndex(), row.getFileIndex());
        }

        diskDrive.set(0, fixedSizeBitSet);

    }

    private String continuousAllocation(String fileName, String s, int blockSpan) {
//        s = getBinaryString(s, 0);
        List<AllocationRow> allocationRows = readFileAllocationTable();
        allocationRows.sort(Comparator.comparing(AllocationRow::getFileIndex));
        System.out.println(allocationRows);


        // Find max open spot,
        int max = -1;
        int index = 0;

        int dist = 0;
        int iter = 0;
        for (int i = 0; i < 256; i++) {
            dist++;
            for (AllocationRow row : allocationRows) {
                if (Integer.parseInt(row.getFileIndex()) == i) {
                    if (dist > max) {
                        max = dist;
                        index = i;
                    }
                    dist = 0;
                    iter = i + 1;
                    break;
                }
            }

            if (dist >= blockSpan && blockSpan > 0) {
                max = dist;
                index = iter;
                break;
            }
        }

        if (dist > max) {
            max = dist;
            index = iter;
        }

        System.out.println("Max: " + max + " blocks, at starting index: " + index);

        if (index < 2 || blockSpan > max) {
            return "\nThere was an error when inserting the file, no room on the file system.";
        }

        // Write based on span

        int blockNo = index;
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i % (512 * 8) == 0 && i != 0) {
                diskDrive.set(blockNo, FixedSizeBitSet.fromBinary(temp.toString()));
                diskDrive.get(1).set(blockNo, true);
                blockNo++;
                temp = new StringBuilder();
            }
            temp.append(s.charAt(i));
        }

        blockSpan = ((blockSpan > 0) ? blockSpan : 1);

        diskDrive.get(1).set(blockNo, true);
        diskDrive.set(blockNo, FixedSizeBitSet.fromBinary(temp.toString()));

//        System.out.println("appending " + fileName + ", " + index + ", " + blockSpan + " to the alloc table");
        diskDrive.get(0).append(1, fileName, Integer.toString(index), Integer.toString(blockSpan));
        return "\nInserted file at block " + index + ", spanning " + blockSpan + " contiguous blocks";
    }


    private String chainedAllocation(String fileName, String s, int blockSpan) {
//        s = getBinaryString(s, 0);
        int free = getNumOfFreeSpace();


        if (blockSpan > free) {
            return "\nThere was an error when inserting the file, no room on the file system.";
        }

        int index;
        int blockPrev;

        List<Integer> openSpots = new ArrayList<>();
        List<Integer> spots = new ArrayList<>();

        boolean b[] = readFreeSpaceTable();
//            System.out.println(Arrays.toString(b));
        for (int j = 0; j < b.length; j++) {
            if (!b[j]) {
                openSpots.add(j);
            }
        }

        int rand = ThreadLocalRandom.current().nextInt(0, openSpots.size() + 1);
        index = openSpots.get(rand);
        spots.add(index);

//            System.out.println(spots);

        int blockNo = index;
        StringBuilder temp = new StringBuilder();
        for (int r = 0; r < s.length(); r++) {
            if ((r + 8) % (512 * 8) == 0 && r != 0) {
                blockPrev = blockNo;
                openSpots.remove(blockNo);
                if (openSpots.size() > 0) {
                    blockNo = openSpots.get(ThreadLocalRandom.current().nextInt(0, openSpots.size()));
                }
                spots.add(blockNo);
                diskDrive.set(blockPrev, FixedSizeBitSet.fromBinary(temp.toString()));
                diskDrive.get(blockPrev).append(1, Integer.toString(blockNo));
                diskDrive.get(1).set(blockPrev, true);
                temp = new StringBuilder();
            }
            temp.append(s.charAt(r));
        }

        blockSpan = ((blockSpan > 0) ? blockSpan : 1);

        if (!spots.contains(blockSpan)) {
            diskDrive.get(1).set(blockNo, true);
            diskDrive.set(blockNo, FixedSizeBitSet.fromBinary(temp.toString()));
            diskDrive.get(blockNo).append(1, Integer.toString(0));
        }


        diskDrive.get(0).append(1, fileName, Integer.toString(spots.get(0)), Integer.toString(blockSpan));
        System.out.println(fileName + ", " + Integer.toString(spots.get(0)) + ", " + Integer.toString(blockSpan));


        return "\nInserted file at block(s) " + spots + ", spanning " + blockSpan + " chained blocks";

    }


    private String indexedAllocation(String fileName, String s, int blockSpan) {
//        s = getBinaryString(s, 0);
        int free = getNumOfFreeSpace();

        if (blockSpan > free) {
            return "\nThere was an error when inserting the file, no room on the file system.";
        }

        int index;
        int blockIndexLoc;
        int blockPrev;

        List<Integer> openSpots = new ArrayList<>();
        List<Integer> spots = new ArrayList<>();

        boolean b[] = readFreeSpaceTable();
//            System.out.println(Arrays.toString(b));
        for (int j = 0; j < b.length; j++) {
            if (!b[j]) {
                openSpots.add(j);
            }
        }

        int rand = openSpots.get(ThreadLocalRandom.current().nextInt(0, openSpots.size()));
        blockIndexLoc = openSpots.get(rand);
        openSpots.remove(blockIndexLoc);

//            System.out.println(spots);

        int blockNo = openSpots.get(ThreadLocalRandom.current().nextInt(0, openSpots.size()));
        spots.add(blockNo);
        StringBuilder temp = new StringBuilder();
        for (int r = 0; r < s.length(); r++) {
            if (r % (512 * 8) == 0 && r != 0) {
                openSpots.remove(blockNo);
                blockPrev = blockNo;
                if (openSpots.size() > 0) {
                    blockNo = openSpots.get(ThreadLocalRandom.current().nextInt(0, openSpots.size()));
                }
                spots.add(blockNo);
                diskDrive.set(blockPrev, FixedSizeBitSet.fromBinary(temp.toString()));
                diskDrive.get(blockPrev).append(1, Integer.toString(blockNo));
                diskDrive.get(1).set(blockPrev, true);
                temp = new StringBuilder();
            }
            temp.append(s.charAt(r));
        }

        blockSpan = ((blockSpan > 0) ? blockSpan : 1);

        if (!spots.contains(blockSpan)) {
            diskDrive.get(1).set(blockNo, true);
            diskDrive.set(blockNo, FixedSizeBitSet.fromBinary(temp.toString()));
        }

        for (Integer l : spots) {
            diskDrive.get(blockIndexLoc).append(1, Integer.toString(l));
        }

        blockSpan = spots.size();

        diskDrive.get(0).append(1, fileName, Integer.toString(blockIndexLoc), Integer.toString(blockSpan));
        System.out.println(fileName + ", " + Integer.toString(blockIndexLoc) + ", " + Integer.toString(blockSpan));


        return "\nInserted file at block(s) " + spots + ", spanning " + spots.size() + " indexed blocks at index file " + blockIndexLoc;
    }
}

class AllocationRow {

    private String fileName;
    private String fileIndex;
    private String fileSpan;

    public AllocationRow(String fileName, String fileIndex, String fileSpan) {
        this.fileName = fileName.trim();
        this.fileIndex = fileIndex.trim();
        this.fileSpan = fileSpan.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileIndex() {
        return fileIndex;
    }

    public void setFileIndex(String fileIndex) {
        this.fileIndex = fileIndex;
    }

    public String getFileSpan() {
        return fileSpan;
    }

    public void setFileSpan(String fileSpan) {
        this.fileSpan = fileSpan;
    }

    @Override
    public String toString() {
        return "AllocationRow{" +
                "fileName='" + fileName + '\'' +
                ", fileIndex='" + fileIndex + '\'' +
                ", fileSpan='" + fileSpan + '\'' +
                '}';
    }
}

class FixedSizeBitSet extends BitSet {
    private final int nbits;

    private int freeLocation = 0;

    FixedSizeBitSet(final int nbits) {
        super(nbits);
        this.nbits = nbits;
    }

    static FixedSizeBitSet fromBinary(String s) {
        FixedSizeBitSet f = new FixedSizeBitSet(512 * 8);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                f.set(i, true);
            }
            f.increaseFreeLocation();
        }

        f.setFreeLocation(s.length());

        return f;

    }

    void append(Integer type, String... strings) {

        for (String s : strings) {
            String binary = getBinaryString(s, type);

            for (int i = 0; i < binary.length(); i++) {
                if (binary.charAt(i) == '1') {
                    this.set(freeLocation + i, true);
                } else if (binary.charAt(i) == '0') {
                    this.set(freeLocation + i, false);
                }
            }
            freeLocation += binary.length();
        }
    }


    FixedSizeBitSet getFromRange(int start, int end) {
        BitSet b = this.get(start, end);
        FixedSizeBitSet fixedSizeBitSet = new FixedSizeBitSet(b.size());

        for (int i = 0; i < b.size(); i++) {
            fixedSizeBitSet.set(i, b.get(i));
        }

        return fixedSizeBitSet;
    }

    private void increaseFreeLocation() {
        freeLocation++;
    }

    public String _toString() {
        final StringBuilder buffer = new StringBuilder(nbits);
        IntStream.range(0, nbits).mapToObj(i -> get(i) ? '1' : '0').forEach(buffer::append);
        return buffer.toString();
    }

    public String __intToString() {
        final StringBuilder buffer = new StringBuilder(nbits);
        IntStream.range(0, nbits).mapToObj(i -> get(i) ? '1' : '0').forEach(buffer::append);

        String binary = buffer.toString();
        int index = 0;
        for (int i = 0; i < buffer.length(); i++) {
            if (binary.charAt(i) == '1') {
                index += Math.pow(2, (7 - i));
            }
        }

        return Integer.toString(index);
    }

    public byte[] toByteArray() {
        byte bytes[] = new BigInteger(this._toString(), 2).toByteArray();
        byte outbytes[] = new byte[bytes.length - 1];
        if (bytes[0] == 0) {
            for (int i = 1; i < bytes.length; i++) {
                outbytes[i - 1] = bytes[i];
            }
            bytes = outbytes;
        }
        return bytes;
    }

    public String __toString() {
        byte bytes[] = new BigInteger(this._toString(), 2).toByteArray();

        byte outbytes[] = new byte[bytes.length - 1];
        if (bytes[0] == 0) {
            for (int i = 1; i < bytes.length; i++) {
                outbytes[i - 1] = bytes[i];
            }
            bytes = outbytes;
        }

        System.out.println(Arrays.toString(bytes));

        return new String(bytes);
    }

    @Override
    public String toString() {
        String s = this._toString();
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (i % 8 == 0 && i != 0) {
                out.append(" ");
            }
            if (i % 128 == 0) {
                out.append("\n\r");
            }
            out.append(s.charAt(i));
        }
        return out.toString();
    }

    public int getFreeLocation() {
        return freeLocation;
    }

    public void setFreeLocation(int i) {
        freeLocation = i;
    }
}