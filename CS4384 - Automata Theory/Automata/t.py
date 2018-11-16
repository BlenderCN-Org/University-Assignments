def main():
    intro()

    intx = 0
    inty = 0
    total = 0

    intx = int(input("Please enter a positive integer for X: "))
    inty = int(input("Please enter a positive integer for Y: "))

    m = mult(intx, inty, total)

    print(m)


def intro():
    print("Hello and welcome to my program! It will multiply X and Y together and output the result.")


def mult(intx, inty, total):
    if intx == 0:
        print("The result is 0")
    elif intx == 1:
        return inty
    else:
        return mult(intx-1, inty, total) + inty

main()
