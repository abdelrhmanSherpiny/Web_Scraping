if __name__ == '__main__':
    N = int(input())

    myList = []
    for _ in range(N):
        comnd, *line = input().split()
        num = list(map(int, line))

        match comnd:
            case "insert":
                myList.insert(num[0], num[1])
            case "print":
                print(myList)
            case "remove":
                myList.remove(num[0])
            case "append":
                myList.append(num[0])
            case "sort":
                myList.sort()
            case "pop":
                myList.pop()
            case "reverse":
                myList.reverse()
