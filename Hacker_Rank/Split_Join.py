def split_and_join(line):
    sp = line.split(" ")
    jo = "-".join(sp)
    return jo

if __name__ == '__main__':
    line = input()
    result = split_and_join(line)
    print(result)
