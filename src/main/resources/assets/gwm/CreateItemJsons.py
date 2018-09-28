import os

def main():
    item_name = input('Enter Item Unlocalized Name:')
    item_full_name = input('Enter full item name (if you havent done that already):')

    if(item_full_name != ""):
        langdoc = open("lang/en_us.lang", "r")
        langcont = langdoc.read()
        langdoc.close()
        langdoc = open("lang/en_us.lang", "w")
        newrow = "item." + item_name + ".name=" + item_full_name
        langdoc.write(langcont + "\n"+ newrow)
        langdoc.close()
        print("lang: " + newrow)

    writeString = '{\n'
    writeString+= '   "parent" : "item/generated",\n'
    writeString+= '   "textures" : {\n'
    writeString+= '      "layer0" : "' + getDir() + ':items/' + item_name + '"\n'
    writeString+= '   }\n'
    writeString+= '}'

    pad = "models/item/" + item_name + ".json"
    document = open(pad, "w")
    print("Created: " + pad)
    document.write(writeString)
    print("Wrote: ")
    print(writeString)
    document.close()

    input("Succes! Press anything to continue.")

def getDir():
    str1=os.getcwd()
    str2=str1.split('\\')
    n=len(str2)
    return str2[n-1]

if __name__ == "__main__":
    main()
