import os

def main():
    block_name = input('Enter Block Unlocalized Name:')

    item_full_name = input('Enter full block name (if you havent done that already):')

    if(item_full_name != ""):
        langdoc = open("lang/en_us.lang", "r")
        langcont = langdoc.read()
        langdoc.close()
        langdoc = open("lang/en_us.lang", "w")
        newrow = "tile." + block_name + ".name=" + item_full_name
        langdoc.write(langcont + "\n"+ newrow)
        langdoc.close()
        print("lang: " + newrow)

    path1 = "blockstates/" + block_name + ".json"
    path2 = "models/block/" + block_name + ".json"
    path3 = "models/item/" + block_name + ".json"

    document = open(path1, "w")
    print("\nOpened:" + path1)
    writeString = "" +\
    "{\n" +\
    '   "variants": {\n' +\
    '      "normal": {"model": "'+getDir()+':'+block_name+'"}\n'+\
    '   }\n'+\
    '}'
    document.write(writeString)
    print(writeString)
    document.close()

    document = open(path2, "w")
    print("\nOpened:" + path2)
    writeString = "" +\
    "{\n" +\
    '   "parent": "block/cube_all",\n' +\
    '   "textures": {\n'+\
    '      "all": "'+getDir()+':blocks/'+block_name+'"\n'+\
    '   }\n'+\
    '}'
    document.write(writeString)
    print(writeString)
    document.close()

    document = open(path3, "w")
    print("\nOpened:" + path3)
    writeString = "" +\
    "{\n" +\
    '   "parent": "'+getDir()+':block/'+block_name+'"\n' +\
    '}'
    document.write(writeString)
    print(writeString)
    document.close()

    input("Succes! Press any key to continue.")

def getDir():
    str1=os.getcwd()
    str2=str1.split('\\')
    n=len(str2)
    return str2[n-1]

if __name__ == "__main__":
    main()
