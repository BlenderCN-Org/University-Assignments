
import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Main {

    private static Scanner kb = new Scanner(System.in);
    private static DiskHelper DiskHelper;

    public static void main(String[] args) {

        if(args.length < 1) {
            System.out.println("Error: You didnt enter a Allocation Type!");
            return;
        }
        
        int type = Integer.parseInt(args[0]);

        if(type > 2 || type < 0) {
            System.out.println("Error: Wrong File Allocaton Parameter!");
            return;
        }

        DiskHelper = new DiskHelper(type);

        String response = "";

        if(type == 0) {
            response = "\nLoaded Continuous File Allocation!";
        } else if (type == 1) {
            response = "\nLoaded Chained File Allocation!";
        } else if (type == 2) {
            response = "\nLoaded Indexed File Allocation!";
        }

        while (true) {
            System.gc();
            System.runFinalization();
            System.out.print("\033[H\033[2J");
            System.out.flush();
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
                    if (tokens.length < 2) {
                        response = "\nThere was an error, no file specified!";
                        break;
                    }
                    response = getFileFromDisk(tokens[1]);
                    break;
                case "2":
                    response = displayFileAlloc();
                    break;
                case "3":
                    response = DiskHelper.getBlock(1);
                    break;
                case "4":
                    if (tokens.length < 2) {
                        response = "\nThere was an error, no file specified!";
                        break;
                    }
                    response = DiskHelper.getBlock(Integer.parseInt(tokens[1]));
                    break;
                case "5":
                    if (tokens.length < 2) {
                        response = "\nThere was an error, no file specified!";
                        break;
                    }
                    response = saveFromDisk(tokens[1], ((tokens.length > 2) ? tokens[2] : ""));
                    break;
                case "6":
                    if (tokens.length < 2) {
                        response = "\nThere was an error, no file specified!";
                        break;
                    }
                    response = writeToDiskFromFile(tokens[1]);
                    break;
                case "7":
                    if (tokens.length < 2) {
                        response = "\nThere was an error, no file specified!";
                        break;
                    }
                    response = deleteFileFromDisk(tokens[1]);
                    break;
                case "8":
                    System.out.println("Exiting Program...");
                    System.exit(0);
                    break;
            }
        }

    }

    /**
     * Writes a file from the system to the disk
     *
     * @param fileName - Target File
     * @return - Returns a console message
     */
    private static String writeToDiskFromFile(String fileName) {

        if (fileName.length() > 8) {
            return "\nThere was an error, file name too long!";
        }

        try {

            String text;
//            text = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
            byte bytes[] = Files.readAllBytes(Paths.get(fileName));
//            System.out.println(Arrays.toString(bytes));

            StringBuilder outtxt = new StringBuilder();
            for (byte b : bytes) {
                outtxt.append(String.format("%8s", Integer.toBinaryString((b & 0xFF) + 0x100).substring(1)));
            }
//            System.out.println(outtxt);
//            byte test[] = new BigInteger(outtxt.toString(), 2).toByteArray();
//            System.out.println(Arrays.toString(test));
//            System.out.println(outtxt);

//            int fileSize = text.length() * 8;
            int fileSize = outtxt.length() / 8;
            int blockSpan = (int) Math.ceil(fileSize / 512.0);

//            System.out.println("File: " + fileName + ", Filesize: " + fileSize + "bytes, Blocksize: " + blockSpan);

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

            return DiskHelper.write(fileName, outtxt.toString(), blockSpan);

        } catch (IOException e) {
            return "There was an error writing to the DiskHelper";
        }
    }

    /**
     * Displays the file allocation table in row form
     *
     * @return - Returns a console message
     */
    private static String displayFileAlloc() {
        List<AllocationRow> allocationRows = DiskHelper.readFileAllocationTable();
        StringBuilder out = new StringBuilder();

        for (AllocationRow row : allocationRows) {
            out.append("\nFile Name: ").append(row.getFileName()).append(", File Index: ").append(row.getFileIndex()).append(", File Span: ").append(row.getFileSpan());
        }

        return out.toString();
    }

    /**
     * Deletes a file from the disk using DiskHelper
     *
     * @param fileName - Target File to Delete
     * @return - Returns a console message
     */
    private static String deleteFileFromDisk(String fileName) {

        if (fileName.length() > 8) {
            return "\nThere was an error, file name too long!";
        }

        return DiskHelper.delete(fileName);
    }

    /**
     * Saves a file to the disk using DiskHelper
     *
     * @param fileName  - Target file on disk
     * @param fileName2 - Optional new FileName
     * @return - Returns a console message
     */
    private static String saveFromDisk(String fileName, String fileName2) {

        if (fileName.length() > 8) {
            return "\nThere was an error, file name too long!";
        }

        return DiskHelper.save(fileName, fileName2);
    }

    /**
     * Attempts to read a file from the disk
     *
     * @param fileName -  The input file for the disk
     * @return - Returns a console message
     */
    private static String getFileFromDisk(String fileName) {

        if (fileName.length() > 8) {
            return "\nThere was an error, file name too long!";
        }

        return DiskHelper.read(fileName);
    }

    /**
     * Returns a binary string from a ascii string s
     *
     * @param s    - A String input
     * @param type - A type
     * @return - Returns a console message
     */
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

}

/**
 * The DiskHelper class serves as a vessel for the blocks. The DiskHelper class holds 256 blocks (FixedBitSets)
 * each with a size of 512bytes (4096bits). The actual disk is simulated as an ArrayList of blocks, and only
 * it's get (read block) and set (write block) methods are used.
 */
class DiskHelper {

    private ArrayList<FixedSizeBitSet> disk;

    private int numOfBlocks = 256;
    private int blockSize = 512 * 8;
    private int type;

    /**
     * Default Constructor
     *
     * @param type - Type of Allocation Method
     */
    DiskHelper(int type) {
        System.out.println("Beginning DiskHelper Initialization...");

        this.type = type;
        this.disk = new ArrayList<>();
        for (int i = 0; i < numOfBlocks; i++) {
            this.disk.add(new FixedSizeBitSet(blockSize));
        }
        createFileAllocationTable();
        createFreeSpaceTable();

        System.out.println("...DiskHelper Initialization Complete\n");
    }

    /**
     * Part of the default constructor, creates the file alloc table
     */
    private void createFileAllocationTable() {
        FixedSizeBitSet fixedSizeBitSet = new FixedSizeBitSet(blockSize);

        fixedSizeBitSet.append(1, "filetabl", "0", "1");
        fixedSizeBitSet.append(1, "freetabl", "1", "1");

        disk.set(0, fixedSizeBitSet);
    }

    /**
     * Part of the default constructor, creates the free space table
     */
    private void createFreeSpaceTable() {
        FixedSizeBitSet freeTableSpace = new FixedSizeBitSet(numOfBlocks);

        freeTableSpace.set(0, true);
        freeTableSpace.set(1, true);

        disk.set(1, freeTableSpace);
    }

    /**
     * Returns a block at a specified index
     *
     * @param index - Block number you wish to get
     * @return - Returns the block to the console
     */
    String getBlock(int index) {
        if (index < 0 || index > 255) {
            return "\nThere was an error, index out of bounds!";
        }
        return disk.get(index).toString();
    }

    /**
     * Write a file to the disk
     *
     * @param fileName  - The file name
     * @param s         - The file as a binary string
     * @param blockSpan - how many blocks this file spans
     * @return - returns a console message
     */
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

    /**
     * Read a file based on a type
     *
     * @param fileName - target file
     * @return - returns a console message
     */
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

    /**
     * Saves a file based on a type
     *
     * @param fileName  - Target File
     * @param fileName2 - Optional File Rename
     * @return - Returns a console message
     */
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

    /**
     * Deletes a file based on a type
     *
     * @param fileName - Target File
     * @return - Returns a console message
     */
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

    /**
     * Reads from a block in a continuous manner
     *
     * @param fileName - target file
     * @return - Returns a console message
     */
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

        if (index == -1 || span == -1) {
            return "\nCould not find the file in the file system";
        }

        FixedSizeBitSet fixedSizeBitSet = new FixedSizeBitSet(size);

        for (int i = index; i < index + span; i++) {
            FixedSizeBitSet file = disk.get(i);
            for (int j = 0; j < file.getFreeLocation(); j++) {
                fixedSizeBitSet.set(j + ((i - index) * 512 * 8), file.get(j));
            }
        }

        return "\n" + fileName + ":\n" + fixedSizeBitSet.__toString();
    }

    /**
     * Deletes from the file system in a continuous manner
     *
     * @param fileName - target file
     * @return - Returns a console message
     */
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

        if (index == -1 || span == -1) {
            return "\nCould not find the file in the file system";
        }

        for (int i = index; i < index + span; i++) {
            disk.get(1).set(i, false);
            disk.set(i, new FixedSizeBitSet(512 * 8));
        }


        deleteFromAllocationTable(fileName);
        return "\n" + fileName + " successfully deleted from the filesystem in a continuous manner";
    }

    /**
     * Saves to the file system in a continuous manner
     *
     * @param fileName  - target file
     * @param fileName2 - optional file rename
     * @return - Returns a console message
     */
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

        if (index == -1 || span == -1) {
            return "\nCould not find the file in the file system";
        }

        int size = 0;

        for (int i = index; i < index + span; i++) {
            FixedSizeBitSet file = disk.get(i);
            size += file.getFreeLocation();
        }

        FixedSizeBitSet fixedSizeBitSet = new FixedSizeBitSet(size);

        for (int i = index; i < index + span; i++) {
            FixedSizeBitSet file = disk.get(i);
            for (int j = 0; j < file.getFreeLocation(); j++) {
                fixedSizeBitSet.set(j + ((i - index) * 512 * 8), file.get(j));
            }
        }


//        System.out.println("Wrote: " + fixedSizeBitSet.__toString());

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

    /**
     * Reads a file in a chained way
     *
     * @param fileName - target file
     * @return - Returns a console message
     */
    private String readChained(String fileName) {
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

        if (index == -1 || span == -1) {
            return "\nCould not find the file in the file system";
        }

        FixedSizeBitSet fixedSizeBitSet = new FixedSizeBitSet(size);


        int iteration = 0;
        while (iteration < span) {
            FixedSizeBitSet file = disk.get(index);
            for (int j = 0; j < (512 * 8) - 8; j++) {
                fixedSizeBitSet.set(j + ((iteration) * 512 * 8), file.get(j));
            }
            index = Integer.parseInt(file.getFromRange(4088, 4096).__intToString());
            iteration++;
        }

        return "\n" + fileName + ":\n" + fixedSizeBitSet.__toString();
    }

    /**
     * Deletes a file in a chained way
     *
     * @param fileName - target file
     * @return - Returns a console message
     */
    private String deleteChained(String fileName) {
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

        if (index == -1 || span == -1) {
            return "\nCould not find the file in the file system";
        }

        int iteration = 0;
        while (iteration < span) {
            FixedSizeBitSet file = disk.get(index);
            disk.get(1).set(index, false);
            disk.set(index, new FixedSizeBitSet(512 * 8));
            index = Integer.parseInt(file.getFromRange(4088, 4096).__intToString());
//            System.out.println("new loc: " + index);
            iteration++;
        }

        deleteFromAllocationTable(fileName);
        return "\n" + fileName + " successfully deleted from the filesystem in a chained manner";
    }

    /**
     * Saves a file in a chained way
     *
     * @param fileName  - target file
     * @param fileName2 - optional file rename
     * @return - Returns a console message
     */
    private String saveChained(String fileName, String fileName2) {
        List<AllocationRow> allocationRows = readFileAllocationTable();
//        System.out.println(allocationRows);

        int index = -1;
        int span = -1;
        for (AllocationRow row : allocationRows) {
            if (row.getFileName().equals(fileName)) {
                index = Integer.parseInt(row.getFileIndex()); // Starting Index
                span = Integer.parseInt(row.getFileSpan());
            }
        }

        if (index == -1 || span == -1) {
            return "\nCould not find the file in the file system";
        }

        int size = 0;
        int tmpIndex = index;
        for (int i = 0; i < span; i++) {
            FixedSizeBitSet file = disk.get(tmpIndex);
            size += file.getFreeLocation();
            tmpIndex = Integer.parseInt(file.getFromRange(4088, 4096).__intToString());
        }

        FixedSizeBitSet fixedSizeBitSet = new FixedSizeBitSet(size);

        int iteration = 0;
        int iter = 0;
        while (iteration < span && index > 0) {
//            System.out.println("read @ " + index);
            FixedSizeBitSet file = disk.get(index);
//            System.out.println("freloc " + file.getFreeLocation() + " at " + index);
            for (int j = 0; j < (file.getFreeLocation() == 4096 ? 4088 : file.getFreeLocation()); j++) {
                fixedSizeBitSet.set((iter), file.get(j));
                iter++;
            }
//            System.out.println(fixedSizeBitSet.__toString());
//            System.out.println("moving from " + index + " to " + file.getFromRange(4088, 4096).__intToString());
            index = Integer.parseInt(file.getFromRange(4088, 4096).__intToString());
            iteration++;
        }

        try {
            BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(((fileName2.equals("") ? fileName : fileName2))));
            bw.write(fixedSizeBitSet.toByteArray());
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println("Wrote: " + fixedSizeBitSet.__toString());

        return "\n" + fileName + " successfully saved onto your machine from a chained method";

    }

    /**
     * Reads a file in an indexed way
     *
     * @param fileName - Target file
     * @return - Returns a console message
     */
    private String readIndexed(String fileName) {
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

        if (index == -1 || span == -1) {
            return "\nCould not find the file in the file system";
        }

        FixedSizeBitSet fixedSizeBitSet = new FixedSizeBitSet(size);

        List<Integer> indexRows = readIndexTable(index, span);
//        System.out.println(indexRows);

        int iteration = 0;
        while (iteration < span) {
            FixedSizeBitSet file = disk.get(indexRows.get(iteration));
            for (int j = 0; j < (512 * 8); j++) {
                fixedSizeBitSet.set(j + ((iteration) * 512 * 8), file.get(j));
            }
            iteration++;
        }

        return "\n" + fileName + ":\n" + fixedSizeBitSet.__toString();
    }

    /**
     * Deletes a file in an indexed way
     *
     * @param fileName - target file
     * @return - Returns a console message
     */
    private String deleteIndexed(String fileName) {
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

        if (index == -1 || span == -1) {
            return "\nCould not find the file in the file system";
        }

        int size = (span) * (512 * 8);

        List<Integer> indexRows = readIndexTable(index, span);
//        System.out.println(indexRows);

        int iteration = 0;
        while (iteration < span) {
            disk.get(1).set(indexRows.get(iteration), false);
            disk.set(indexRows.get(iteration), new FixedSizeBitSet(512 * 8));
            iteration++;
        }

        disk.get(1).set(index, false);
        disk.set(index, new FixedSizeBitSet(512 * 8));
        deleteFromAllocationTable(fileName);

        return "\n" + fileName + " successfully deleted from the filesystem in a indexed manner";
    }

    /**
     * Deletes a file in an indexed way
     *
     * @param fileName  - target filename
     * @param fileName2 - optional file rename
     * @return - Returns a console message
     */
    private String saveIndexed(String fileName, String fileName2) {
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

        if (index == -1 || span == -1) {
            return "\nCould not find the file in the file system";
        }

//        int size = (span) * (512 * 8);
        List<Integer> indexRows = readIndexTable(index, span);
//        System.out.println("index: " + index + ", " + indexRows);

        int size = 0;
        for (int i = 0; i < span; i++) {
            FixedSizeBitSet file = disk.get(indexRows.get(i));
            size += file.getFreeLocation();
        }

//        System.out.println("Size: " + size);
        FixedSizeBitSet fixedSizeBitSet = new FixedSizeBitSet(size);

        int iteration = 0;
        int iter = 0;
        while (iteration < span) {
            FixedSizeBitSet file = disk.get(indexRows.get(iteration));
//            System.out.println(iteration + ", read from file " + indexRows.get(iteration) + " with free space " + file.getFreeLocation());
            for (int j = 0; j < file.getFreeLocation(); j++) {
                fixedSizeBitSet.set(iter, file.get(j));
                iter++;
            }
            iteration++;
        }

//        System.out.println("Totfresp: " + fixedSizeBitSet.getFreeLocation());

//        System.out.println(fixedSizeBitSet.toString());

        try {
            BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(((fileName2.equals("") ? fileName : fileName2))));
            bw.write(fixedSizeBitSet.toByteArray());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println("Wrote: " + fixedSizeBitSet.__toString());

        return "\n" + fileName + " successfully saved onto your machine from a indexed method";
    }

    /**
     * Reads from the index table
     *
     * @param index - target index table location
     * @param span  - span on the file
     * @return - the blocks from an index table
     */
    private List<Integer> readIndexTable(int index, int span) {
        FixedSizeBitSet indexTable = disk.get(index);
//        System.out.println(indexTable.toString());
        List<Integer> spots = new ArrayList<>();

        for (int i = 0; i < span; i++) {
            spots.add(Integer.parseInt(indexTable.getFromRange((i) * 8, (i + 1) * 8).__intToString()));
        }

//        System.out.println(spots);
        return spots;
    }

    /**
     * Gets the number of free spaces from the freespacetable
     *
     * @return - an integer of the number of free spaces
     */
    private int getNumOfFreeSpace() {
        FixedSizeBitSet fixedSizeBitSet = disk.get(1);

        int free = 0;
        for (int i = 0; i < fixedSizeBitSet.size(); i++) {
            if (!fixedSizeBitSet.get(i)) {
                free++;
            }
        }

        return free;
    }

    /**
     * Returns the free space table as a bool array
     *
     * @return - a bool array
     */
    private boolean[] readFreeSpaceTable() {
        FixedSizeBitSet fixedSizeBitSet = disk.get(1);

        boolean b[] = new boolean[256];

        for (int i = 0; i < b.length; i++) {
            b[i] = fixedSizeBitSet.get(i);
        }
        return b;
    }

    /**
     * Reads the file allocation table for operations
     *
     * @return the file allocation table in side a helper object
     */
    List<AllocationRow> readFileAllocationTable() {
        FixedSizeBitSet fixedSizeBitSet = disk.get(0);
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

    /**
     * Deletes an object from the file allocation table
     *
     * @param fileName - target file you wish to delete
     */
    private void deleteFromAllocationTable(String fileName) {

        FixedSizeBitSet fixedSizeBitSet = new FixedSizeBitSet(512 * 8);

        List<AllocationRow> allocationRows = readFileAllocationTable();

        for (AllocationRow row : allocationRows) {
            if (row.getFileName().equals(fileName)) {
                allocationRows.remove(row);
                break;
            }
        }

//        System.out.println(allocationRows);

        for (AllocationRow row : allocationRows) {
            String fname = row.getFileName();
            if (fname.length() < 8) {
                StringBuilder fileNameBuilder = new StringBuilder(fname);
                for (int i = fileNameBuilder.length(); i < 8; i++) {
                    fileNameBuilder.insert(0, " ");
                }
                fname = fileNameBuilder.toString();
            }
            fixedSizeBitSet.append(1, fname, row.getFileIndex(), row.getFileSpan());
        }

//        System.out.println(fixedSizeBitSet.toString());
        disk.set(0, fixedSizeBitSet);

    }

    /**
     * Stores a file in a continuous way
     *
     * @param fileName  - target file
     * @param s         - binary string
     * @param blockSpan - span
     * @return - Returns a console message
     */
    private String continuousAllocation(String fileName, String s, int blockSpan) {
//        s = getBinaryString(s, 0);
        List<AllocationRow> allocationRows = readFileAllocationTable();
        allocationRows.sort(Comparator.comparing(AllocationRow::getFileIndex));
//        System.out.println(allocationRows);


        // Find max open spot,
        int max = -1;
        int index = 0;

        int dist = 0;
        int iter = 0;
        boolean free[] = readFreeSpaceTable();

        for (int i = 0; i < free.length; i++) {
            dist++;
            if (free[i]) {
                if (dist > max) {
                    max = dist;
                    index = i;
                }
                dist = 0;
                iter = i + 1;
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
                disk.set(blockNo, FixedSizeBitSet.fromBinary(temp.toString()));
                disk.get(1).set(blockNo, true);
                blockNo++;
                temp = new StringBuilder();
            }
            temp.append(s.charAt(i));
        }

        blockSpan = ((blockSpan > 0) ? blockSpan : 1);

        disk.get(1).set(blockNo, true);
        disk.set(blockNo, FixedSizeBitSet.fromBinary(temp.toString()));

//        System.out.println("appending " + fileName + ", " + index + ", " + blockSpan + " to the alloc table");
        disk.get(0).append(1, fileName, Integer.toString(index), Integer.toString(blockSpan));
        return "\nInserted file at block " + index + ", spanning " + blockSpan + " contiguous blocks";
    }

    /**
     * Saves a string in a chained way
     *
     * @param fileName  - target fil
     * @param s         - binary string
     * @param blockSpan - span
     * @return - Returns a console message
     */
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
        int iter = 1;
        for (int r = 0; r < s.length(); r++) {
            if ((r) % (4088) == 0 && r != 0) {
//                System.out.println("We're breaking at R = " + r);
                iter++;
                blockPrev = blockNo;
                openSpots.remove(new Integer(blockNo));
                if (openSpots.size() > 0) {
                    blockNo = openSpots.get(ThreadLocalRandom.current().nextInt(0, openSpots.size()));
                }
                spots.add(blockNo);
//                System.out.println(FixedSizeBitSet.fromBinary(temp.toString()).__toString() + " at " + r);
                disk.set(blockPrev, FixedSizeBitSet.fromBinary(temp.toString()));
                disk.get(blockPrev).append(1, Integer.toString(blockNo));
//                System.out.println("Appended " + blockNo + " to the end of " + blockPrev);
                disk.get(1).set(blockPrev, true);
                temp = new StringBuilder();
            }
            temp.append(s.charAt(r));
        }

//        blockSpan = ((blockSpan > 0) ? blockSpan : 1);

        if (disk.get(blockNo).getFreeLocation() < 4096) {
            disk.get(1).set(blockNo, true);
            disk.set(blockNo, FixedSizeBitSet.fromBinary(temp.toString()));
            disk.get(blockNo).append(1, Integer.toString(0));
//            System.out.println("Appended " + 0 + " to the end of " + blockNo);
        }

        disk.get(0).append(1, fileName, Integer.toString(spots.get(0)), Integer.toString(blockSpan));
//        System.out.println(fileName + ", " + Integer.toString(spots.get(0)) + ", " + Integer.toString(blockSpan));


        return "\nInserted file at block(s) " + spots + ", spanning " + blockSpan + " chained blocks";

    }

    /**
     * Saves a string in a indexed way
     *
     * @param fileName  - target file
     * @param s         - binary string
     * @param blockSpan - span
     * @return - Returns a console message
     */
    private String indexedAllocation(String fileName, String s, int blockSpan) {
//        s = getBinaryString(s, 0);
        int free = getNumOfFreeSpace();

        if (blockSpan > free) {
            return "\nThere was an error when inserting the file, no room on the file system.";
        }

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

        int blockIndexLoc = openSpots.get(ThreadLocalRandom.current().nextInt(0, openSpots.size()));
        openSpots.remove(new Integer(blockIndexLoc));
//        System.out.println(blockIndexLoc);
        disk.get(1).set(blockIndexLoc, true);

//            System.out.println(spots);

        int blockNo = openSpots.get(ThreadLocalRandom.current().nextInt(0, openSpots.size()));
        spots.add(blockNo);
        disk.get(blockIndexLoc).append(1, Integer.toString(blockNo));
        StringBuilder temp = new StringBuilder();
        for (int r = 0; r < s.length(); r++) {
            if (r % (512 * 8) == 0 && r != 0) {
                openSpots.remove(new Integer(blockNo));
                blockPrev = blockNo;

                if (openSpots.size() > 0) {
                    blockNo = openSpots.get(ThreadLocalRandom.current().nextInt(0, openSpots.size()));
                }
                spots.add(blockNo);
                disk.set(blockPrev, FixedSizeBitSet.fromBinary(temp.toString()));
                disk.get(1).set(blockPrev, true);
                disk.get(blockIndexLoc).append(1, Integer.toString(blockNo));
                temp = new StringBuilder();
            }
            temp.append(s.charAt(r));
        }

        if (disk.get(blockNo).getFreeLocation() < 4096) {
            disk.get(1).set(blockNo, true);
            disk.set(blockNo, FixedSizeBitSet.fromBinary(temp.toString()));
            disk.get(blockNo).append(1, Integer.toString(0));
        }

        blockSpan = spots.size();

        disk.get(0).append(1, fileName, Integer.toString(blockIndexLoc), Integer.toString(blockSpan));
//        System.out.println(fileName + ", " + Integer.toString(blockIndexLoc) + ", " + Integer.toString(blockSpan));


        return "\nInserted file at block(s) " + spots + ", spanning " + spots.size() + " indexed blocks at index file " + blockIndexLoc;
    }
}

/**
 * A helper class that a parsed file allocation row is put into for easier manipulation
 */
class AllocationRow {

    private String fileName;
    private String fileIndex;
    private String fileSpan;

    AllocationRow(String fileName, String fileIndex, String fileSpan) {
        this.fileName = fileName.trim();
        this.fileIndex = fileIndex.trim();
        this.fileSpan = fileSpan.trim();
    }

    String getFileName() {
        return fileName;
    }

    String getFileIndex() {
        return fileIndex;
    }

    String getFileSpan() {
        return fileSpan;
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

/**
 * A class that extends the java bitset class. This external class is used to manage blocks.
 * This class includes methods as a 'quality of life' upgrade for inserting and manipulating the
 * bits once off the DiskHelper.
 */
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

            String binary = Main.getBinaryString(s, type);

            for (int i = 0; i < binary.length(); i++) {
                if (binary.charAt(i) == '1') {
                    this.set(this.freeLocation + i, true);
                } else if (binary.charAt(i) == '0') {
                    this.set(this.freeLocation + i, false);
                }
            }
            this.freeLocation += binary.length();
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

    /**
     * @return - the binary string
     */
    private String _toString() {
        final StringBuilder buffer = new StringBuilder(nbits);
        IntStream.range(0, nbits).mapToObj(i -> get(i) ? '1' : '0').forEach(buffer::append);
        return buffer.toString();
    }

    /**
     * @return - the binary string of a pure int
     */
    String __intToString() {
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

    /**
     * @return - the block as a byte array
     */
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

    /**
     * @return the block as a ascii string
     */
    String __toString() {
        byte bytes[] = new BigInteger(this._toString(), 2).toByteArray();

        byte outbytes[] = new byte[bytes.length - 1];
        if (bytes[0] == 0) {
            for (int i = 1; i < bytes.length; i++) {
                outbytes[i - 1] = bytes[i];
            }
            bytes = outbytes;
        }

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

    int getFreeLocation() {
        return freeLocation;
    }

    private void setFreeLocation(int i) {
        freeLocation = i;
    }
}
