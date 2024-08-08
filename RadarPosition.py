import mysql.connector
import serial

# Connect to Arduino
comName = 'COM3'
portName = 9600
ser = serial.Serial(comName, portName)

# Connect to sql
mydb = mysql.connector.connect(
    host="localhost",
    user="root",
    password="P02122003K",
    database="Dragline"
)

cursor = mydb.cursor()


def insertTable(pos, dist):
    sql = "INSERT INTO radar (pos, dist) VALUES (%s, %s)"
    val = (pos, dist)
    cursor.execute(sql, val)
    mydb.commit()


while True:
    if ser.in_waiting > 0:
        data = ser.readline().decode().strip()
        info = data.split(",")
        pos = int(info[0])
        dist = float(info[1])
        print(data)
        insertTable(pos, dist)
