import numpy as np
from alexnet import alexnet


def train_skills(training_data='balanced_training_data.npy'):
	WIDTH = 85
	HEIGHT = 16
	LR = 1e-3
	EPOCHS = 8
	MODEL_NAME = 'pyDinoRunner-{}-{}-{}-epochs.model'.format(LR, 'alexnetv2', EPOCHS)

	model = alexnet(WIDTH, HEIGHT, LR)

	train_data = np.load(training_data)

	pivo = int(0.1 * len(train_data))
	train = train_data[:-pivo]
	test = train_data[-pivo:]

	X = np.array([i[0] for i in train]).reshape(-1, WIDTH, HEIGHT, 1)
	Y = np.array([i[1] for i in train])


	test_x = np.array([i[0] for i in test]).reshape(-1, WIDTH, HEIGHT, 1)
	test_y = np.array([i[1] for i in test])

	model.fit({'input': X}, {'targets': Y}, n_epoch=EPOCHS, validation_set=({'input': test_x}, {'targets': test_y}), 
    snapshot_step=500, show_metric=True, run_id=MODEL_NAME)

	
	model.save(MODEL_NAME)

	return MODEL_NAME
