import numpy as np
import pandas as pd
from collections import Counter
from random import shuffle
import cv2
############

def balance_data(verbose=True, train_file='training_data.npy'):
	train_data = np.load(train_file)

	# uncomment the following lines if you want to see the video and controls record
	#
	# for data in train_data:
	# 	img = data[0]
	# 	choice = data[1]
	# 	cv2.imshow('test', img)
	# 	print(choice)

	# 	if cv2.waitKey(25) & 0xFF == ord('q'):
	# 		cv2.destroyAllWindows()
	# 		break


	df = pd.DataFrame(train_data)

	ups = []
	downs = []
	walks = []

	shuffle(train_data)

	for data in train_data:
		img = data[0]
		choice = data[1]

		if choice == [0,0]:
			walks.append([img, choice])

		elif choice == [1,0]:
			ups.append([img, choice])

		elif choice == [0,1]:
			downs.append([img, choice])

		else:
			print("No matches! Something went wrong")


	walks = walks[:len(ups)]
	downs = downs[:len(ups)]
	ups   = ups[:len(ups)]

	final_data = walks + ups + downs

	shuffle(final_data)

	save_file = "balanced_" + train_file

	np.save(save_file, final_data)

	if verbose:
		print("Initial train data len: ", len(train_data))
		print("Final train data len: ", len(final_data))
		print(df.head())
		print(Counter(df[1].apply(str)))

	return save_file
