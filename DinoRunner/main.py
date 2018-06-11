from DinoEye import vision
from DinoBrain import train_skills, survival_skills, load_memory
from DinoLeg import jump, crawl
from balance_data import balance_data
from alexnet import alexnet
import numpy as np
import os
import cv2
import time

class Dino:

	def createTrainFile(self, training_file="training_data.npy"):
		if os.path.isfile(training_file):
			print('File exists, loading previous data!')
			training_data = list(np.load(training_file))
		else:
			print('File does not exist, starting fresh')
			training_data = []


		stopped = False
		paused = False

		while not stopped:

			image_and_action = vision()

			if image_and_action[1] == [5,5]:   # pause/run the process
				if paused:
					paused = False
					time.sleep(1)
				else:
					paused = True
					np.save(training_file, training_data)
					time.sleep(1)

			elif image_and_action[1] == [6,6]: # break the process
				stopped = True
				np.save(training_file, training_data)
				time.sleep(1)


			if not paused and not stopped:
				training_data.append(image_and_action)

				if len(training_data) % 500 == 0:
					print(len(training_data))
					np.save(training_file, training_data)

				cv2.imshow('window', image_and_action[0])
				if cv2.waitKey(25) & 0xFF == ord('q'):
					cv2.destroyAllWindows()
					break


	def trainJumpSkill(self, training_data="balanced_training_data.npy"):
		jump_skill = train_skills()
		# print(jump_skill)

		return jump_skill


	def survive(self, model):
		# load_memory(model_file)
		WIDTH = 85
		HEIGHT = 16
		LR = 1e-3
		EPOCHS = 3

		# model = alexnet(WIDTH, HEIGHT, LR)
		model_file = 'pyDinoRunn-0.001-alexnetv2-8-epochs.model'

		print("\n\nfuncionou\n\n")

		# model.load(model_file)

		print("\n------------------------------------------\n")
		print("Loading model file {}".format(model_file))
		print("'p' for pause\n's' for exit\n\nSurviving in...")

		[(print(t),time.sleep(1)) for t in range(5, 0, -1)]


		stopped = False
		paused = False
		while not stopped:

			image_and_action = vision()

			if image_and_action[1] == [5,5]:   # pause/run the process
				if paused:
					paused = False
					time.sleep(1)
				else:
					paused = True
					time.sleep(1)

			elif image_and_action[1] == [6,6]: # break the process
				stopped = True
				time.sleep(1)


			if not paused and not stopped:
				image = image_and_action[0]
				moves = list(np.around(model.predict([image.reshape(WIDTH,HEIGHT,1)])[0]))
				print(moves)
				if moves == [1,0]:
					jump()
				elif moves == [0,1]:
					crawl()


				# moves = survival_skills(image_and_action[0])

				# print(moves)

				# if moves == 'up':
				# 	jump()
				# elif moves == 'down':
				# 	crawl()



if __name__ == '__main__':

	train_file = "training_data-apagar.npy"
	dino = Dino()

	# dino.createTrainFile(training_file=train_file)

	balanced_file = "balanced_training_data.npy"
	# balanced_file = balance_data(train_file)

	model_file = 'pyDinoRunn-0.001-alexnetv2-8-epochs.model'

	model = dino.trainJumpSkill(training_data=balanced_file)

	print("MODEL FILE!!! : {}".format(model_file))

	dino.survive(model)
