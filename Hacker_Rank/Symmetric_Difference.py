m = int(input())
mSet = set(map(int,input().split()))

n = int(input())
nSet = set(map(int, input().split()))

dSet = nSet.difference(mSet)
dSet.update(mSet.difference(nSet))
dSet = sorted(dSet)


for s in dSet:
    print(s)
