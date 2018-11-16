To execute the program:
- Navigate to the project location in Bash or Command Prompt
- Move files to directory with main.py in it
- Execute the following statement:
        python main.py [pda_filename] [input_filename]

        pda_filename is the file needed to construct the pda
        input_filename is the list of inputs to be tested

the program should proceed to execute and print out whether the strings are accepted
by the pda or not.



example demo program:

   >> python main.py pda.txt input.txt
   >> PDA CREATED (
      4 states,
      0 starting state,
      Accepting States: [3],
      Transition States: [[(0, 1), ('', '', '$', 0, 1)], [(1, 1), ('0', '', '0', 1, 1)], [(1, 2), ('', '', '', 1, 2)], [(2, 2), ('1', '0', '', 2, 2)], [(2, 3), ('', '$', '', 2, 3)]])


      0011: String is accepted by the PDA
      e: String is accepted by the PDA
      0000011: String was not accepted by the PDA
