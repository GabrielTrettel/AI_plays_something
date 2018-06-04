from DinoEye import screen_record
from DinoEye import keybord_record
import numpy as np
import os
import cv2

class Dino:
	# def __init__(self):
		# self.training_file_name = training_file_name


	def createTrainFile(self, training_file_name="training_data.npy"):
		if os.path.isfile(training_file_name):
			print('File exists, loading previous data!')
			training_data = list(np.load(training_file_name))
		else:
			print('File does not exist, starting fresh')
			training_data = []
			

		while True:
			image = screen_record()

			# returns [1,0] for up, [0,1] for down and [0,0] for nothing
			keys = [0,0]
			keys = keybord_record()

			training_data.append([image, keys])

			if len(training_data) % 500 == 0:
				print(len(training_data))
				np.save(training_file_name, training_data)

			cv2.imshow('window', image)
			if cv2.waitKey(25) & 0xFF == ord('q'):
				cv2.destroyAllWindows()
				break				




dino = Dino()
dino.createTrainFile()