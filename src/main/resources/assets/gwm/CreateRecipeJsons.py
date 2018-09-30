import os
from pathlib import Path

def main():
    correct = False
    print("Create a shaped crafting recipe")
    while(not correct):
        i = 0
        rows = []
        while(i < 3):
            rows.append(input("Input row " + str(i + 1) + ": "))
            i += 1
        for row in rows:
            print(row)
        answer = input("Y/n, is this correct?")
        if(not (answer.lower() == "no" or answer.lower() == "n")):
            correct = True
        for row in rows:
            if (len(row) > 3):
                correct = False
                print("Rows must be shorter than 3!")

    for row in rows:
        for letter in row:
            if(letter != " "):
                alreadyExists = False
                for comb in combinations:
                    if(comb.exists(letter)):
                        alreadyExists = True
                if(not alreadyExists):
                    value = input("What is " + letter + "?: ")
                    Combination(letter, value)

    

    json = \
    '{' + "\n" + \
    '    "type": "minecraft:crafting_shaped",' + "\n" + \
    '    "pattern": [' + "\n"

    firstRow = True
    for row in rows:
        if firstRow:
            json += '        "' + row + '"'
            firstRow = False
        else:
            if row != "":
                json += ',\n'
                json += '        "' + row + '"'
    json += "\n    ],\n" + \
    '    "key": {' + "\n"

    firstComb = True
    for comb in combinations:
        if(not firstComb):
            json += ", \n"
        json += '        "' + comb.char + '": {\n'
        json += '            "item": "' + comb.name + '",\n'
        json += '            "data": 0\n'
        json += '        }'
        if(firstComb):
            firstComb = False
    json += '\n    },\n'
    json += '    "result": {\n'
    result = input("What do you want to produce?: ")
    json += '        "item": "' + result + '",\n'
    json += '        "count": ' + input("How many will you make in one go?: ") + ',\n'
    json += '        "data": 0 \n'
    json += '    }\n'
    json += '}'

    result = result.split(":", 1)[1]

    foundNew = False
    path = "recipes/" + result
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

combinations = []

class Combination:
    def __init__(self, char, name):
        self.char = char
        self.name = name
        combinations.append(self)

    def exists(self, char):
        return self.char == char

if __name__ == "__main__":
    main()
