import time
import picamera
import requests
import base64

def send_image(base64_string):
  url = "http://134.61.95.23:1234/image"
  payload = { 'cam_id': '1', 'image_base64': base64_string }
  r = requests.post(url, data = payload)
  print r.text

def filenames():
  while True:
    try:
      time.sleep(1)
      yield 'image.jpg'
      with open('image.jpg', 'rb') as open_file:
        byte_content = open_file.read()
      base64_bytes = base64.b64encode(byte_content)
      base64_string = base64_bytes.decode('utf-8')
      send_image(base64_string)
    except Exception as e:
      pass

with picamera.PiCamera() as camera:
  camera.resolution = (640, 480)
  camera.framerate = 30
  camera.start_preview()

  time.sleep(2) # Camera Initialize
  camera.capture_sequence(filenames(), use_video_port=True)
