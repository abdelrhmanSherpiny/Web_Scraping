input()
mySet = set(map(int, input().split()))

for i in range(int(input())):
    cmd = input().split()

    match cmd[0]:
        case "update":
            mySet.update(map(int, input().split()))
        case "intersection_update":
            mySet.intersection_update(map(int, input().split()))
        case "difference_update":
            mySet.difference_update(map(int, input().split()))
        case "symmetric_difference_update":
            mySet.symmetric_difference_update(map(int, input().split()))

print(sum(mySet))
