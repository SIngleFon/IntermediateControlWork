from os import path
import json
import datetime

file_base = "Python/Notes.json"
last_id = 0
all_data = []
untitled = 1

if not path.exists(file_base):
    with open(file_base, "w", encoding="utf-8") as _:
        pass


def read_notes():
    global last_id, all_data

    with open(file_base, encoding="utf-8") as f:
        all_data = [i.strip() for i in f]
        if all_data:
            last_id = int(all_data[-1].split()[0])
            return all_data
    return []


def add_notes():
    global last_id, untitled
    print("\033c", end="")
    array, string = ["Header", "Body"], []
    last_id += 1
    for i in array:
        string.append(input(f"Enter {i}:"))
    current_date = datetime.datetime.now()
    current_date_string = current_date.strftime('%m/%d/%y %H:%M:%S')
    string.append(current_date_string)
    if(string[0] == ""):
        string[0] = "Untitled" + str(untitled)
        untitled += 1
    if len(all_data) == 0:
        with open(file_base, "a", encoding="utf-8") as f:
            f.write(f"{last_id} |")
            for i in string:
                f.write(f"{i}|")
        print(f"==============\n"
              "Заметка успешно добавлена!")
    else:
        if check_record(string) == True:
            with open(file_base, "a", encoding="utf-8") as f:
                f.write(f"{last_id} |")
                for i in string:
                    f.write(f"{i}|")
                print(f"==============\n"
                      "Заметка успешно добавлена!")
        else:
            print(f"==============\n"
                  "Такая заметка уже есть, Либо вы ничего не ввели")

def check_record(string):
    global all_data
    for i in range(last_id-1):
        copy = all_data[i].replace(',', '').replace('[', '').replace(']', '').split("|")
        if copy[1] == string[0]:
            return False
    return True

def search_notes():
    key = input("Search by ID or text:\n" + "1. ID\n2. Text\n")
    list = []
    match key:
        case "1":
            search = int(input("Input ID: "))
            list.append(all_data[search-1])
        case "2":
            search = input("Search: ").lower()
            
            for i in range(last_id):
                try:
                    for j in all_data[i]:
                        if search in all_data[i].lower():
                            list.append(all_data[i])
                        break
                except IndexError:
                    pass
    show_all(list)


def check_int(msg):
    while True:
        try:
            return int(input(msg))
        except (TypeError, ValueError):
            print("Error, you did not enter a number")


def delete_notes(id):
    global last_id, all_data
    # search_notes()
    show_all(all_data)
    if (all_data):
        while True:
            id = check_int("Select and write id to change/remove: ")
            if id > len(all_data):
                print("Error, enter the correct ID")
            else:
                break
        with open(file_base, encoding="utf-8") as f:
            all_data = [i.strip() for i in f]
        with open(file_base, "w", encoding="utf-8") as f:
            for line in all_data:
                if line.strip("\n") != all_data[id - 1]:
                    f.write(f"{line}\n")
        last_id -= 1
    else:
        print("Empty")


def show_all(all_data):
    print("\033c", end="")
    arr = ["ID", "Header", "Body", "Time(Add/Edit)"]
    if all_data:
        for i in all_data:
            copy = i.replace(',', '').replace(
                '[', '').replace(']', '').split("|")
            for j in range(4):
                if j != 3:
                    print(f"{arr[j]}: {copy[j]}")
                else:
                    print(f"{arr[j]}: {copy[j]} {copy[j+1]}")
            print("================")
    else:
        print("Empty data")


def change_notes():
    delete_notes(id)
    add_notes()


def change_id_records():
    with open(file_base, encoding="utf-8") as f:
        all_data = [i.strip() for i in f]
    with open(file_base, "w", encoding="utf-8") as f:
        i = 1
        for line in all_data:
            line = line[:0] + str(i) + line[1:]
            i += 1
            f.write(f"{line}\n")


def open_notes():
    show_all(all_data)
    if all_data:
        key = int(input("Choose ID notes for open: "))
        print("\033c", end="")
        arr = ["ID", "Header", "Body", "Time(Add/Edit)"]
        copy = all_data[key-1].replace(',', '').replace('[', '').replace(']', '').split("|")
        for i in range(4):
            if i != 3:
                    print(f"{arr[i]}: {copy[i]}")
            else:
                    print(f"{arr[i]}: {copy[i]} {copy[i+1]}")
        print("================")


def main_menu():
    print("\033c", end="")
    play = True
    while play:
        change_id_records()
        read_notes()
        answer = input("==============\n"
                       "Notes:\n"
                       "1. Show all notes\n"
                       "2. Add a notes\n"
                       "3. Show a notes\n"
                       "4. Change\n"
                       "5. Delete\n"
                        "6. Clear Console\n"
                       "7. Exit\n"
                       "==============\n")
        match answer:
            case "1":
                show_all(all_data)
            case "2":
                add_notes()
            case "3":
                open_notes()
            case "4":
                change_notes()
            case "5":
                delete_notes(id=None)
            case "6":
                print("\033c", end="")
            case "7":
                play = False
            case _:
                print("Try again!\n")


main_menu()
