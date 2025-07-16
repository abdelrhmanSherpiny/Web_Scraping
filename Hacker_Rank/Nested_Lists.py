if __name__ == '__main__':
    mylist = []
    for _ in range(int(input())):
        name = input()
        score = float(input())
        mylist.append([name, score])
    scores = sorted(set([s[1] for s in mylist]))

    second_lowest = scores[1]

    names = [s[0] for s in mylist if s[1] == second_lowest]

    for name in sorted(names):
        print(name)