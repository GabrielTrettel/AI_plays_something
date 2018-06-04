import numpy as np
import pyscreenshot as ImageGrab
import cv2
import time
#import pyautogui as pg  # for keybord input 


# def player_test():
#     # count dow for preparing the envirorment
#     for i in range(4, 0, -1):
#         print(i)
#         time.sleep(1)
#     # kb seg jump
#     pg.press('space')


def process_img(original_image):
    processed_img = cv2.cvtColor(original_image, cv2.COLOR_BGR2GRAY)
    processed_img = cv2.bilateralFilter(processed_img, 3, 75, 75) 
    return processed_img

def screen_record(): 
#    last_time = time.time()
    while(True):
        # 86,248,486,316 windowed mode for TV           Gabriel T.
        # 133,212,550,318 windowed mode for monitor  178,142,329,184   Gabriel T.
        # 170,356,786,501                               Erick S.
        #
        #                                            xi, yi, xii,yii
        printscreen =  np.array(ImageGrab.grab(bbox=(65,296,612,406)))
#        print('loop took {} seconds'.format(time.time()-last_time))

#        last_time = time.time()
        new_screen = process_img(printscreen)
        cv2.imshow('window', new_screen)
        # cv2.imshow('window', printscreen)

        
        if cv2.waitKey(25) & 0xFF == ord('q'):
            cv2.destroyAllWindows()
            break

screen_record()
# player_test()
