# coding: utf-8

# In[22]:

import numpy as np
from numpy import linalg as LA

# Given the matrix that we are working with
from numpy.random import random

data = np.array([[1, 2, 3, ],
                 [5, 7, 11, ],
                 [4, 8, 12], ], dtype=float)


def GramSchmidt(data):
    printSteps = 0  # Defaults to 0 to not show every step of the process

    # Input validation
    isValid = False
    while not isValid:
        seeDetails = input("Do you want to see all of the steps? Y or N?")  # Asking the user if they want to or not
        type(seeDetails)
        if seeDetails == 'Y' or seeDetails == 'N':
            isValid = True
            if seeDetails == 'Y':  # Show details when the user asks for it
                printSteps = 1

    # Initial printing of the given matrix
    if printSteps == 1:
        print("Starting position")
        print(data)

    # How many columns, so it knows how many times to iterate
    numcols = len(data[0])

    # Normalize the vectors
    norm_vector = np.sqrt(np.sum(data * data, axis=0))

    # Find the pivot
    pivot = (max(norm_vector))

    # Initializing the order of the pivot
    x = 0
    pivot_order = []

    for i in range(numcols):
        # Picking the column
        col = []
        col_pos = 0
        max_sum = float('-Inf')
        for j in range(numcols):
            curr_col = data[:, j]  # [row[j] for row in data]
            col_sum = np.sum(curr_col ** 2)
            # Comparing the column to each other one by one, and resetting to the larger one
            if col_sum > max_sum:
                col = curr_col
                max_sum = col_sum
                col_pos = j
        pivot_order.append(col_pos)
        # Calculate q
        a = (np.sqrt(np.sum(col * col)))
        if a != 0:
            q1 = col / a
        else:
            q1 = np.array([0 for x in col], dtype=float)

        tq1 = q1.T

        # Multiply tranpose q1 with columns, starting at a1
        for k in range(numcols):
            x += 1
            curr_col = [row[k] for row in data]
            a = tq1 * curr_col
            # Add up the vector
            addup = a.sum()
            # Added up vector multiplied with original column
            carni = addup * tq1
            # Subtract from Column 1
            new_value = curr_col - carni
            # Update data matrix
            data[:, k] = new_value
            # Showing every step and labeled
            if printSteps == 1:
                print("\nRun #: ", x)
                print(data)
            data[:, k] = new_value
            if col_sum < 0.0000000001:
                break;
    if printSteps == 1:
        # Returns the pivot order of the matrix
        print("Pivot Order:" + str(pivot_order))
    return pivot_order, data


# Input matrix A, k eigenvectors (or matrix size)
# Output: Eigenvalues, Eigenvectors
def InversePowerIteration(A, k):
    n, m = A.shape
    Q = np.random.rand(n, k)
    Q, _ = np.linalg.qr(Q)
    Q_prev = Q

    for i in range(1000):
        Z = A.dot(Q)
        Q, R = np.linalg.qr(Z)

        # can use other stopping criteria as well
        err = ((Q - Q_prev) ** 2).sum()

        Q_prev = Q
        if err < 1e-3:
            break

    return np.diag(R), Q


# Calling the function to be ran
np.set_printoptions(formatter={'float': lambda x: format(x, '6.7E')})

P, M = GramSchmidt(data)
print("\n")
print('Pivot Order: {0}'.format(P))
print("\n")
print(":::Eigenvalues & Eigenvectors:::")
eigenvalues, eigenvectors = InversePowerIteration(data, len(data))
for i in range(0, len(eigenvalues)):
    print('Eigen-value: {0}\nEigen-vector: {1}\n'.format(eigenvalues[i], eigenvectors[i]))
# Validate
print(":::Validation:::")
eigenvalues, eigenvectors = LA.eig(data)
for i in range(0, len(eigenvalues)):
    print('Eigen-value: {0}\nEigen-vector: {1}\n'.format(eigenvalues[i], eigenvectors[i]))
