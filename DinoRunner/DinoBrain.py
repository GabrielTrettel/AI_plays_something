import numpy as np
from alexnet import alexnet

WIDTH = 85
HEIGHT = 16
LR = 1e-3
EPOCHS = 8
N_CLASSES = 3
model = alexnet(WIDTH, HEIGHT, LR, N_CLASSES)

def train_skills(training_data='balanced_training_data-3classes.npy'):


	MODEL_NAME = 'pyDinoRunn-{}-{}-{}-epochs.model'.format(LR, 'alexnetv2', EPOCHS)

	train_data = np.load(training_data)

	pivo = int(0.1 * len(train_data))
	train = train_data[:-pivo]
	test = train_data[-pivo:]

	X = np.array([i[0] for i in train]).reshape(-1, WIDTH, HEIGHT, 1)
	Y = np.array([i[1] for i in train])


	test_x = np.array([i[0] for i in test]).reshape(-1, WIDTH, HEIGHT, 1)
	test_y = np.array([i[1] for i in test])


	model.fit({'input': X}, {'targets': Y}, n_epoch=EPOCHS, validation_set=({'input': test_x}, {'targets': test_y}), snapshot_step=500, show_metric=True, run_id="model3")

	model.save("modelo.tfl")

	return model

def load_memory(model_name='pyDinoRunner-0.001-alexnetv2-8-epochs.model'):
	model.load(model_name)

def survival_skills(image):

	moves = list(np.around(model.predict([image.reshape(WIDTH,HEIGHT,1)])[0]))

	if moves == [1,0]:
		return 'up'
	elif moves == [0,1]:
		return 'down'
	else:
		return 'not'
