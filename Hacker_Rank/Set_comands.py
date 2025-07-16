input()
mySet = set(map(int, input().split()))

for i in range(int(input())):
    s = input().split()
    match s[0] :
        case "remove":
            mySet.remove(int(s[1]))
        case "discard":
            mySet.discard(int(s[1]))
        case "pop":
            mySet.pop()

print(sum(mySet))
