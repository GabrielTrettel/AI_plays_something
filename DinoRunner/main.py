from DinoEye import screen_record
from DinoEye import keyboard_record
from DinoBrain import train_skills
from balance_data import balance_data
import numpy as np
import os
import cv2


class Dino:
	# def __init__(self):
		# self.training_file_name = training_file_name


	def createTrainFile(self, training_file="training_data.npy"):
		if os.path.isfile(training_file):
			print('File exists, loading previous data!')
			training_data = list(np.load(training_file))
		else:
			print('File does not exist, starting fresh')
			training_data = []
			

		while True:
			image = screen_record()

			# returns [1,0] for up, [0,1] for down and [0,0] for nothing
			keys = [0,0]
			keys = keyboard_record()

			training_data.append([image, keys])

			if len(training_data) % 500 == 0:
				print(len(training_data))
				np.save(training_file, training_data)

			cv2.imshow('window', image)
			if cv2.waitKey(25) & 0xFF == ord('q'):
				cv2.destroyAllWindows()
				break				


	def trainJumpSkill(self, training_data="balanced_training_data.npy"):
		jump_skill = train_skills()



if __name__ == '__main__':
	
	train_file = "training_data2.npy"
	dino = Dino()

	# dino.createTrainFile(training_file=train_file)

	balanced_file = "balanced_training_data.npy"
	# balanced_file = balance_data(train_file)

	dino.trainJumpSkill(training_data=balanced_file)

