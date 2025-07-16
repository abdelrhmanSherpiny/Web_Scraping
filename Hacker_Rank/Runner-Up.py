if __name__ == '__main__':
    n = int(input())
    arr = list(map(int, input().split()))
    arr2 = list()
    fMax = max(arr)
    for x in arr:
        if x != fMax:
            arr2.append(x)

    print(max(arr2))
