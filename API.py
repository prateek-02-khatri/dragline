import requests
from playsound import playsound


def play_buzzer(distance):
    if distance < 53.0 and distance > 33.0:
        Time.sl
        playsound("src/Files/buzzer.mp3")
    elif distance < 33.0:
        playsound("src/Files/object.mp3")


def test_api(file_path, distance):
    # Read the image
    with open(file_path, "rb") as image_file:
        image_content = image_file.read()

    # Send a POST request to the API
    response = requests.post("http://127.0.0.3:2340/detect", files={"file": image_content})

    if response.status_code == 200:
        print("API response status: 200 (Successful)")
        play_buzzer(distance)
    else:
        print(f"API response status: {response.status_code}")
