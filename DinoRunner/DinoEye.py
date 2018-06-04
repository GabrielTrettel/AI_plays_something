import mss
import numpy
import cv2
import time


def process_img(original_image):
    processed_img = cv2.cvtColor(original_image, cv2.COLOR_BGR2GRAY)
    processed_img = cv2.bilateralFilter(processed_img, 3, 75, 75) 
    return processed_img


def screen_record():
	last_time = time.time()
	a = 0
	while True:
		a += 1
		print('loop took {} seconds'.format(time.time()-last_time))

		last_time = time.time()
		with mss.mss() as sct:
			# Screen config for Gabriel T: 'top': 250, 'left': 90, 'width': 512, 'height': 96
			# Screen config for Gabriel T:
			monitor = {'top': 250, 'left': 90, 'width': 512, 'height': 96}
			img = numpy.array(sct.grab(monitor))

		processed_img = process_img(img)
		cv2.imshow('window', processed_img)

		if cv2.waitKey(25) & 0xFF == ord('q'):
			cv2.destroyAllWindows()
			break


screen_record()