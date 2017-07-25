import time
import picamera
import requests
import base64

def send_image(base64_string):
  url = "https://amedeo.informatik.rwth-aachen.de/image"
  payload = { 'cam_id': '1', 'image_base64': base64_string }
  r = requests.post(url, data = payload)
  print r.text

def capture_image(camera):
  stream = io.BytesIO()
  while True:
    try:
      camera.capture(stream, 'jpeg')
      base64_bytes = base64.b64encode(stream.getvalue())
      base64_string = base64_bytes.decode('utf-8')
      send_image(base64_string)
      time.sleep(1)
    except Exception as e:
      pass

with picamera.PiCamera() as camera:
  camera.resolution = (640, 480)
  camera.framerate = 30
  # camera.start_preview()

  time.sleep(2) # Camera Initialize
  capture_image(camera)
