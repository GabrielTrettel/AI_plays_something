import mss
import numpy as np
import cv2
import keyboard as kb
import time

def eye_calibration():
	stopped = False

	while not stopped:
		record = kb.get_hotkey_name()

		if(record == 'e'):
			stopped = True

		with mss.mss() as sct:

			monitor = {'top': 350, 'left': 179, 'width': 613, 'height': 159}
			screen = sct.grab(monitor)

		img = np.array(screen)
		processed_img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
		processed_img = cv2.bilateralFilter(processed_img, 3, 75, 75)
		processed_img = cv2.resize(processed_img, (85,16))

		cv2.imshow('window', processed_img)
		if cv2.waitKey(25) & 0xFF == ord('q'):
			cv2.destroyAllWindows()
			break	


def vision():
	with mss.mss() as sct:
		# Screen config for Gabriel T: 'top': 250, 'left': 90, 'width': 512, 'height': 96
		# Screen config for Gabriel T:
		# Screen config for Eric S: 'top': 350, 'left': 179, 'width': 613, 'height': 159
		monitor = {'top': 350, 'left': 179, 'width': 613, 'height': 159}
		screen = sct.grab(monitor)

	keys = [0,0]
	keys = keyboard_record()

	img = np.array(screen)
	processed_img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
	processed_img = cv2.bilateralFilter(processed_img, 3, 75, 75)
	processed_img = cv2.resize(processed_img, (85,16))

	# cv2.imshow('window', processed_img)


	return [processed_img, keys]



def keyboard_record():
	record = kb.get_hotkey_name()
	if record == "up":
		return [1,0]
	elif record == "down":
		return [0,1]
	elif record == "p":
		return [5,5]
	elif record == "s":
		return [6,6]
	else:
		return [0,0]
