import mss
import numpy
import cv2
import keyboard as kb
import time

def screen_record():
	with mss.mss() as sct:
		# Screen config for Gabriel T: 'top': 250, 'left': 90, 'width': 512, 'height': 96
		# Screen config for Gabriel T:
		# Screen config for Eric S: 'top': 350, 'left': 179, 'width': 613, 'height': 159
		monitor = {'top': 350, 'left': 179, 'width': 613, 'height': 159}
		img = numpy.array(sct.grab(monitor))


	processed_img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
	processed_img = cv2.bilateralFilter(processed_img, 3, 75, 75)
	processed_img = cv2.resize(processed_img, (85,16))

	# cv2.imshow('window', processed_img)

	return processed_img



def keyboard_record():
	record = kb.get_hotkey_name()
	if record == "up":
		return [1,0]
	elif record == "down":
		return [0,1]
	else:
		return [0,0]
