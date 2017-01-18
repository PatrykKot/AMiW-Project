/*
 * main.c
 *
 *  Created on: 1 wrz 2016
 *      Author: Patryk Kotlarz
 */

#include <stdio.h>
#include "cmsis_os.h" 
#include "stm32f7xx_hal.h"
#include "stm32746g_discovery_ts.h"

#include "mcuConfig.h"
#include "lcdLogger.h"

uint32_t xVal = 0;
uint32_t yVal = 0;

void tsThread(void const * argument)
{
	char msg[30];
	TS_StateTypeDef tsState;
	lcdInit();
	BSP_TS_Init(BSP_LCD_GetXSize(), BSP_LCD_GetYSize());
	logMsg("Startup");
	
	while(1)
	{
		osDelay(10);
		if(BSP_TS_GetState(&tsState) == TS_OK)
		{
			xVal = tsState.touchX[0];
			yVal = tsState.touchY[0];
			sprintf(msg, "X: %d, Y: %d", (int)xVal, (int)yVal);
			logMsg(msg);
		}
		else
		{
			logErr("Error");
		}
	}
}

osThreadId tsThreadHandle;
osThreadDef(tsThread, osPriorityNormal, 0, 0);

/**
 * Main function
 */
int main(void) {
	/* CORE INITIALIZATION */

	/* HAL initialization */
	HAL_Init();

	/* Setting the system clock */
	SystemClock_Config();

	/* Setting the system kernel */
	osKernelInitialize();
	
	/* Thread initialization */

	tsThreadHandle = osThreadCreate(osThread(tsThread), NULL);
	
	/* Start system kernal */
	osKernelStart();

	while (1)
	{
		osThreadYield ();
	}
}
