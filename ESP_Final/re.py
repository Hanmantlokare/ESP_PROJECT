
import sys,fileinput


def main():

    file = sys.argv[1]

    f = open(file, 'r')
    filedata = f.read()
    f.close()

    newdata = filedata.replace("xxxx", "651.451.15.62")

    f = open(file, 'w')
    f.write(newdata)
    f.close()

if __name__ == "__main__":
     main()
