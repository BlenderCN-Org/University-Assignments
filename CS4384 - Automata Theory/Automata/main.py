import sys


class PDA:

    # Default Constructor
    def __init__(self, num_of_states, start_state, final_states, transition_map):
        self.num_of_states = int(num_of_states)
        self.start_state = int(start_state)
        self.final_states = final_states
        self.transition_map = transition_map
        self.cur_state = int(start_state)
        self.found = 0
        self.started = 0
        self.stack = []
        print(
            "PDA CREATED (\n{0} states,\n{1} starting state, \nAccepting States: {2}, \nTransition States: {3})\n\n".format(
                num_of_states, start_state, final_states, transition_map))

    # Function that verifies if an input is accepted by the PDA
    def verify(self, string):
        string = string.strip('\n').strip('\r')
        if string == 'e':
            string = ''
        output = self.verify_transition(string, self.start_state, self.stack)
        p_val = "String is accepted by the PDA" if output >= 1 else "String was not accepted by the PDA"
        string = string if string != '' else 'e'
        print(string + ': ' + p_val)

        # Reset the PDA for more inputs
        self.cur_state = self.start_state
        self.found = 0
        self.stack = []
        self.started = 0

    # Helper function, searches for a index based on key
    def find_in_map(self, state, i, identifier):
        for transition in self.transition_map:
            if transition[0] == (state, i) and identifier == transition[1]:
                return transition[2]

    # Verifies the input string in the PDA
    def verify_transition(self, string, state, stack):
        # print(string, state, stack)
        if self.found > 0:
            return 1

        # Base cases
        # If done processing and on final state
        if string == '' and len(stack) == 0 and state in self.final_states:
            self.found = 1
            return 1
        # If done processing and not on final state
        elif string == '' and len(stack) == 0 and state not in self.final_states and self.started != 0:
            return 0

        e_transitions = []

        complete = 0
        # Loops through the possible state transitions
        for i in range(self.num_of_states):
            transition = None
            for item in self.transition_map:
                if (state, i) == item[0]:
                    transition = item[1]
                    if transition[0] == '' and (transition[1] == '' or transition[1] != '') and (
                            transition[2] == '' or transition[2] != ''):
                        e_transitions.append(transition)

                    # If the transition forwards us in our cause, we pursue it
                    if len(string) > 0 and transition[0] == string[0]:

                        # If we have a push operation, push the char onto the stack, increment the pointer and
                        # transition
                        if transition[1] == '':
                            if transition[2] != '':
                                stack.append(transition[2])
                            complete += self.verify_transition(string[1:], transition[4], stack[:])

                        # If we have a pop operation
                        else:
                            # If we can pop, pop, increase ptr, and transition and try again
                            if stack[len(stack) - 1] == transition[1]:
                                # print('popping', transition)
                                stack.pop()
                                complete += self.verify_transition(string[1:], transition[4], stack[:])
                            # If we cannot pop, we stop this transition

        # If we cannot transition anymore
        if len(e_transitions) == 0:
            return 0

        # Executes the recursive calls on e transitions
        for transition in e_transitions:

            # If the transition pops and the last character is the $, we pop
            if transition[0] == '' and transition[1] == '$' and transition[2] == '' and stack[len(stack) - 1] == \
                    '$' and string == '':
                stack.pop()
                complete += self.verify_transition(string, transition[4], stack[:])

            # If we have a pop operation that does not end the $
            elif transition[0] == '' and (transition[1] != '$' and transition[1] != '') and transition[2] == '' and (
                    stack[len(stack) - 1] != '$' and stack[len(stack) - 1] != ''):
                stack.pop()
                complete += self.verify_transition(string, transition[4], stack[:])

            # If we push anything, push and call
            elif transition[1] == '':
                if transition[2] != '':
                    stack.append(transition[2])
                # print('Took push e-trans')
                complete += self.verify_transition(string, transition[4], stack[:])

        return complete + self.found


def main():
    f = open(sys.argv[1])
    flines = f.readlines()

    pda = None

    # File parsing junk
    init = 0
    num_of_states = 0
    start_state = 0
    final_states = None
    transitions = []
    for line in flines:
        line = line.rstrip('\n')
        # We initialize the PDA
        if init == 0:
            tokens = line.split(" ")
            accepting = []
            for i in range(3, len(tokens)):
                accepting.append(int(tokens[i]))
            num_of_states = tokens[0]
            start_state = tokens[2]
            final_states = accepting
            init = 1
        # We add transitions
        else:
            tokens = line.split(" ")
            from_state = int(tokens[0])
            to_state = int(tokens[1])
            print_char = '' if tokens[2] == 'e' else tokens[2]
            char1 = '' if tokens[3] == 'e' else tokens[3]
            char2 = '' if tokens[4] == 'e' else tokens[4]

            # State Map
            transitions.append([(from_state, to_state), (print_char, char1, char2, from_state, to_state)])

    pda = PDA(num_of_states, start_state, final_states, transitions)
    f.close()

    f = open(sys.argv[2])
    flines = f.readlines()
    for x in range(1, int(flines[0]) + 1):
        line = flines[x]
        try:
            pda.verify(line)
        except RecursionError:
            # If a recursion error occurs, that means there is an infinite cycle and no solution was found,
            # so we reject the input
            print(line.strip("\n").strip("\r") + ": String was not accepted by the PDA")

            # Reset the PDA for more inputs
            pda.cur_state = pda.start_state
            pda.found = 0
            pda.stack = []
            pda.started = 0
    f.close()


if __name__ == '__main__':
    main()
