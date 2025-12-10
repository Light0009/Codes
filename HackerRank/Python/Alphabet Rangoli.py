import string

def print_rangoli(size):
    alphabet = string.ascii_lowercase
    index = (size * 2 - 1) + (size * 2 - 2)
    for i in range(size - 1, -size, -1):        
        row = ["-"] * index
        for j in range(0, size - abs(i)):
            row[2 * (size - 1 + j)] = alphabet[abs(i) + j]
            row[2 * (size - 1 - j)] = alphabet[abs(i) + j]
        print("".join(row))

if __name__ == '__main__':
    n = int(input())
    print_rangoli(n)
