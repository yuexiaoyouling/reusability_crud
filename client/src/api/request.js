import axios from "axios";

const request = axios.create({
    baseURL:  '/api',
	timeout: 30 * 1000, //请求超时时间：30秒
	withCredentials: false, // 表示跨域请求时是否需要使用凭证
	hearder:'Access-Control-Allow-Origin'
});

// 请求拦截器
request.interceptors.request.use(
	config => { //配置信息处理
		console.log("这是陈尊德发起的请求")
		return config;
	},
	error => { //错误处理
		console.log(error);
		return Promise.reject(error);
	}
);

// 响应拦截器
request.interceptors.response.use((res) => { // 请求成功的回调函数
	// console.log(res);
	// return  res.data;
	console.log("这是陈尊德发起的请求的反应")
	return  res;
},() => { // 请求失败的回调函数
    return Promise.reject('fail');
});

export default request;
