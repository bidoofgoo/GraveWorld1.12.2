import os
from pathlib import Path

def main():
    correct = False
    print("Create a shapeless crafting recipe")
    while(not correct):
        i = 0
        rows = []
		
        while(i < 9):
            scanner = input("Input item " + str(i + 1) + " (or press stop): ")
            if(scanner.lower() == "stop" or scanner == ""):
                break
            rows.append(scanner)
            i += 1
        for row in rows:
            print(row)
        answer = input("Y/n, is this correct?")
        if(not (answer.lower() == "no" or answer.lower() == "n")):
            correct = True
    

    json = \
    '{' + "\n" + \
    '    "type": "minecraft:crafting_shapeless",' + "\n" + \
    '    "ingredients": [' + "\n"

    firstRow = True
    for row in rows:
        if firstRow:
            json += '{"item": "' + row + '"}\n'
            firstRow = False
        else:
            json += ',{"item": "' + row + '"}\n'
    json += "],\n"

    
    json += '    "result": {\n'
    result = input("What do you want to produce?: ")
    json += '        "item": "' + result + '",\n'
    json += '        "count": ' + input("How many will you make in one go?: ") + ',\n'
    json += '        "data": 0 \n'
    json += '    }\n'
    json += '}'

    result = result.split(":", 1)[1]

    foundNew = False
    path = "recipes/" + result + "_shapeless"
    i = 0
    while not foundNew:
        i += 1
        file = Path(path + str(i) + ".json")
        if not file.is_file():
            foundNew = True
            path += str(i) + ".json"

    document = open(path, "w")
    print("\nCreated: " + path)
    document.write(json)
    print("Wrote: \n" + json)
    document.close()
        
    
    input("\nSucces! Press anything to continue.")

def getDir():
    str1=os.getcwd()
    str2=str1.split('\\')
    n=len(str2)
    return str2[n-1]

if __name__ == "__main__":
    main()
