//export const rootApi = 'https://4a641c14-cf46-4050-90ec-45b740e3be35.mock.pstmn.io/';

import { getApiHost } from '../utils/commons';

export const rootApi = getApiHost() +'api/';
export const rootSettings = rootApi + 'settings/';
export const getSettingsMethod = rootSettings + 'get';
export const saveSettingsMethod = rootSettings + 'set';
export const rootStatistic = rootApi + 'statistic/';
export const getGraphDataByPeriodMethod = rootStatistic + 'getGraphDataByPeriod';
export const rootDevices = rootApi + 'device/';
export const getDevicesMethod = rootDevices + 'getAll';
export const getDeviceSettingsMethod = rootDevices + 'getDetail';
export const saveDeviceSettingsMethod = rootDevices + 'saveDetails';
