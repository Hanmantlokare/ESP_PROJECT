
import sys,fileinput


def main():
	
    file = sys.argv[1]

    f = open(file, 'r')
    filedata = f.read()
    f.close()
   
    f = open('public_IP.txt','r')
    data = f.read()
    f.close()

    newdata = filedata.replace("xxxx",data)

    f = open(file, 'w')
    f.write(newdata)
    f.close()

if __name__ == "__main__":
     main()
