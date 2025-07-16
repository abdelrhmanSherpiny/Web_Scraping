import string


def print_rangoli(s):
    alphabet = string.ascii_lowercase
    left = ""
    right = ""
    txt = alphabet[s - 1].center(s * 4 - 3, "-")
    lowertxt = ""
    if s != 1:
        lowertxt += txt
    print(txt)
    for i in range(s - 2, -1, -1):
        left = left + alphabet[i + 1] + "-"
        right = "-" + alphabet[i + 1] + right
        txt = (left + alphabet[i] + right).center(s * 4 - 3, "-")
        if i != 0:
            lowertxt = txt + '\n' + lowertxt
        print(txt)

    print(lowertxt)


if __name__ == '__main__':
    n = int(input())
    print_rangoli(n)