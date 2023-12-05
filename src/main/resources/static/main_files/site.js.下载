var OS = function () {
	var ua = navigator.userAgent,
		isWechat = /MicroMessenger/.test(ua),
		isWindowsPhone = /(?:Windows Phone)/.test(ua),
		isSymbian = /(?:SymbianOS)/.test(ua) || isWindowsPhone,
		isAndroid = /(?:Android)/.test(ua),
		isFireFox = /(?:Firefox)/.test(ua),
		isChrome = /(?:Chrome|CriOS)/.test(ua),
		isTablet = /(?:iPad|PlayBook)/.test(ua) || (isAndroid && !/(?:Mobile)/.test(ua)) || (isFireFox && /(?:Tablet)/.test(ua)),
		isPhone = /(?:iPhone)/.test(ua) && !isTablet,
		isPc = !isPhone && !isAndroid && !isSymbian;
	return {
		isChrome: isChrome,
		isWechat: isWechat,
		isTablet: isTablet,
		isPhone: isPhone || isAndroid,
		isAndroid: isAndroid,
		isPc: isPc,
		mode: isPc ? 'pc' : 'mobile'
	};
}();
if (OS.isPhone) {
	if (location.pathname.indexOf('cesuan') > -1) {
		location.href = location.href.replace("wwww", "m").replace("www", "m");
	} else {
		location.href = 'http://m.juzhiyuan.com/';
	}
}


var GK_TYPE = {
	"11": "3+3(6)",
	"12": "3+3(6)",
	"13": "3+1+2",
	"14": "normal",
	"15": "normal",
	"21": "3+1+2",
	"22": "normal",
	"23": "normal",
	"31": "3+3(6)",
	"32": "3+1+2",
	"33": "3+3(7)",
	"34": "normal",
	"35": "3+1+2",
	"36": "normal",
	"37": "3+3(6)",
	"41": "normal",
	"42": "3+1+2",
	"43": "3+1+2",
	"44": "3+1+2",
	"45": "normal",
	"46": "3+3(6)",
	"50": "3+1+2",
	"51": "normal",
	"52": "normal",
	"53": "normal",
	"54": "normal",
	"61": "normal",
	"62": "normal",
	"63": "normal",
	"64": "normal",
	"65": "normal"
};
var subjectsList = [{ "label": "政", "value": "1" }, { "label": "史", "value": "2" }, { "label": "地", "value": "3" }, { "label": "物", "value": "4" }, { "label": "化", "value": "5" }, { "label": "生", "value": "6" }, { "label": "技", "value": "7" }];
function getSubjectsLabel(value) {
	const str = String(value || '');
	if (str.length === 1) {
		return str === '1' ? '文科' : '理科';
	}
	var list = String(value || '').split('');
	return list.sort(function (p, n) {
		return p - n
	}).map(function (v) {
		const match = subjectsList.find(function (item) { return +item.value === +v });
		return match ? match.label : ''
	}).join('/');
}
var qsParse = function (s) {
	var list = String(s || '').split('&');
	var result = {};
	list.forEach(function (item) {
		var a = item.split('=');
		if (a.length === 2) {
			result[a[0]] = a[1]
		}
	});
	return result;
}

function EstimateInfo() {
	this.data = null;
	var search = location.search.substr(location.search.indexOf('?') + 1);
	//url 测算信息
	var _URL = this.format(qsParse(search));
	var locale = localStorage.getItem('estimate');
	//本地 测算信息
	var _LOCALE = locale ? this.format(JSON.parse(locale)) : null;
	this._URL = _URL;
	this._LOCALE = _LOCALE;
	this._USER = null;
	if (window.userInfo) {
		//本地 测算信息
		var _USER = this.format(window.userInfo);
		this._USER = _USER;
		//如果url带入测算信息 比较省份是否一致 省份一致采用url中测算信息
		switch (true) {
			//处理是新用户的场景
			case !+window.userInfo.province: {

				break;
			}
			//如果url携带的测算信息合法 & url省份与用户一致
			case !!_URL && _URL.province === window.userInfo.province: {
				this.set(_URL);
				break;
			}
			//如果url携带的测算信息合法 & url省份与用户不一致
			case !!_URL && +_URL.province !== +window.userInfo.province: {
				var userSubjects = this.getSubjectsByUser('');
				if (userSubjects && _URL && userSubjects !== _URL.subjects) {
					//用户的科目与URL科目不一致 弃用
					_URL = null;
				}
				if (_URL && window.userInfo.province !== +_URL.province) {
					//用户的省份与URL省份不一致 弃用
					_URL = null;
				}
				if (userSubjects && _LOCALE && userSubjects !== _LOCALE.subjects) {
					//用户的科目与本地科目不一致 弃用
					_URL = null;
				}
				if (_LOCALE && window.userInfo.province !== +_LOCALE.province) {
					//用户的省份与本地省份不一致 弃用
					_URL = null;
				}
				break;
			}
			default: {
				var userSubjects = this.getSubjectsByUser('');
				//如果本地和用户的测算信息省份一致
				if (_USER && _LOCALE && +_USER.province === +_LOCALE.province) {
					//并且 分数或科目不相同时 弹出框
					if (!(+_USER.score === +_LOCALE.score && _USER.subjects === _LOCALE.subjects)) {
						this.__USER = _USER;
						_USER = null;
						this._USER = null;
						// _LOCALE = null;
						this._LOCALE = null;
					}

				}
				if (userSubjects && _URL && userSubjects !== _URL.subjects) {
					//用户的科目与URL科目不一致 弃用
					_URL = null;
				}
				if (_URL && window.userInfo.province !== +_URL.province) {
					//用户的省份与URL省份不一致 弃用
					_URL = null;
				}
				if (userSubjects && _LOCALE && userSubjects !== _LOCALE.subjects) {
					//用户的科目与本地科目不一致 弃用
					_URL = null;
				}
				if (_LOCALE && window.userInfo.province !== +_LOCALE.province) {
					//用户的省份与本地省份不一致 弃用
					_URL = null;
				}
				this.set(_USER || _LOCALE);
				//之前为了不让用户信息设置给data清掉_USER 还原
				if (this.__USER) {
					this._USER = this.__USER;
				}
			}
		}
	} else {
		//如果未登录尝试从url中取
		this.set(_URL || _LOCALE)
	}
}
EstimateInfo.prototype.get = function (filter) {
	// var result = null;
	// if (this.data) {
	// 	result = this.format(this.data);
	// } else {
	// 	// console.log(this.data,!userInfo,'--')
	// 	var locale = localStorage.getItem('estimate');
	// 	if (locale) {
	// 		var data = JSON.parse(locale);
	// 		this.set(data)
	// 		result = this.format(data);
	// 	}
	// }
	// if (filter) {
	// 	return filter(result);
	// }
	// return result;
	return this.data;
}
//获取缓存测算信息 用以测算信息不能被主动确定时 回显给用户待用户确定
EstimateInfo.prototype.getCache = function (filter) {
	// return this.get(filter) || this._URL;
	var locale = localStorage.getItem('estimate');
	var _LOCALE = locale ? this.format(JSON.parse(locale)) : null;
	return this.data || this._USER || this._URL || _LOCALE;
}
EstimateInfo.prototype.format = function (data) {
	if (!data) {
		return null;
	}
	if (!+data.province) {
		return null;
	}
	var subjects = '';
	if (GK_TYPE[data.province] === 'normal') {
		subjects = String(data.subjects || data.wenli);
		if (subjects.length !== 1) {
			return null;
		}
	} else {
		subjects = String(data.subjects || data.kemu || data.mykemu);
		if (subjects.length !== 3) {
			return null;
		}
	}
	//科目排序
	subjects = subjects.split("")
		.sort(function (p, n) {
			return p - n;
		})
		.join("")
	if (!+data.score) {
		return null
	}
	delete data.rank;
	console.log(provinceContorl, 'provinceContorl');
	return Object.assign({}, data, {
		wenli: subjects.length === 1 ? subjects : '',
		mykemu: subjects.length !== 1 ? subjects : '',
		mykemuList: subjects.length !== 1 ? subjects.split('').map(function (v) {
			return +v;
		}) : [],
		province: +data.province,
		provinceTitle: provinceContorl ? provinceContorl.getTitleByCode(data.province) : '',
		subjects: subjects,
		subjectsLabel: getSubjectsLabel(subjects)
	});
}
EstimateInfo.prototype.isNew = function (province) {
	return GK_TYPE[province] !== 'normal'
}
EstimateInfo.prototype.isSubjectValid = function (province, subjects) {
	var subjectsStr = String(subjects || '');
	return this.isNew(province) ? subjectsStr.length === 3 : subjectsStr.length === 1;
}
EstimateInfo.prototype.getSubjectsByUser = function () {
	var wenli = window.userInfo.wenli;
	var mykemu = window.userInfo.mykemu;
	return mykemu ? String(mykemu) : String(wenli);
}

EstimateInfo.prototype.set = function (data) {
	var parse = this.format(data);
	localStorage.setItem('estimate', JSON.stringify(parse));
	this.data = parse;
	if (window.history && (window.location.pathname === '/cesuan' || window.location.pathname === '/')) {
		//清除url参数
		window.history.replaceState(null, "", window.location.pathname);
	} else {
		// window.location.href = '/cesuan';
	}
	if (provinceContorl) {
		provinceContorl.set(data.province);
	}
	return this.data;
}
EstimateInfo.prototype.clear = function (f) {
	console.log('clear ' + f);
	this.data = null;
	localStorage.removeItem('estimate');
}
EstimateInfo.prototype.valid = function (code) {
	if (this.data) {
		var province = this.data.province;
		var subjects = this.data.subjects;
		var result = +province === +code && this.isSubjectValid(province, subjects);
		if (!result) {
			this.clear('valid');
		}
		return result
	} else {
		return false
	}
}
var estimateInfo = new EstimateInfo();

var provinceList = [{ "code": 34, "title": "安徽" }, { "code": 11, "title": "北京" }, { "code": 50, "title": "重庆" }, { "code": 35, "title": "福建" }, { "code": 62, "title": "甘肃" }, { "code": 44, "title": "广东" }, { "code": 45, "title": "广西" }, { "code": 52, "title": "贵州" }, { "code": 46, "title": "海南" }, { "code": 13, "title": "河北" }, { "code": 41, "title": "河南" }, { "code": 23, "title": "黑龙江" }, { "code": 42, "title": "湖北" }, { "code": 43, "title": "湖南" }, { "code": 36, "title": "江西" }, { "code": 22, "title": "吉林" }, { "code": 32, "title": "江苏" }, { "code": 21, "title": "辽宁" }, { "code": 15, "title": "内蒙古" }, { "code": 64, "title": "宁夏" }, { "code": 63, "title": "青海" }, { "code": 37, "title": "山东" }, { "code": 14, "title": "山西" }, { "code": 61, "title": "陕西" }, { "code": 31, "title": "上海" }, { "code": 51, "title": "四川" }, { "code": 12, "title": "天津" }, { "code": 54, "title": "西藏" }, { "code": 65, "title": "新疆" }, { "code": 53, "title": "云南" }, { "code": 33, "title": "浙江" }];
function ProvinceContorl() {
	// this.data = {
	// 	code: '',
	// 	title: '',
	// 	source: '',//locale user 
	// };
	this.data = null;
	this.callback = [];
	this.init();
}
ProvinceContorl.prototype.ready = function (fun) {
	this.callback.push(fun);
}
ProvinceContorl.prototype.on = function (fun) {
	this.callback.push(fun);
}
ProvinceContorl.prototype.emit = function (data) {
	var _this = this;
	this.callback.forEach(function (call) {
		call({
			now: data,
			old: _this.data
		});
	});
}
ProvinceContorl.prototype.init = function () {
	var userProvince = $('input[name="user_province"]').val();
	var cookieProvince = $('input[name="user_cookie_province"]').val();
	switch (true) {
		case !!+userProvince: {
			this.set(userProvince, 'user');
			break;
		}
		case !!estimateInfo._URL: {
			this.set(estimateInfo._URL.province, 'url');
			break;
		}
		case !!+cookieProvince: {
			this.set(cookieProvince, 'cookie');
			break;
		}
		default: {
			this.getByIp();
			break;
		}
	}
}
ProvinceContorl.prototype.getByIp = function () {
	var _this = this;
	$.getScript('https://ip.ws.126.net/ipquery', function () {
		if (!_this.data) {
			var province = lo.replace(/省|市/gmi, '');
			_this.set(province, 'ip')
		}
	});
}
function listFind(list, func) {
	for (var i = 0; i < list.length; i++) {
		var item = list[i];
		var result = func(item);
		if (result) {
			return item;
		}
	}
	return null;
}
ProvinceContorl.prototype.getTitleByCode = function (code) {
	var match = listFind(provinceList, function (item) {
		return +item.code === +code
	});
	return match ? match.title : ''
}
ProvinceContorl.prototype.getCodeByTitle = function (title) {
	var match = listFind(provinceList, function (item) {
		return item.title === title
	});
	return match ? match.code : ''
}
ProvinceContorl.prototype.set = function set(info, source, by) {
	// var code = province.code, title = province.title || siteobj.getProvinceTitle(province.code);
	// alert('设置省份信息' + JSON.stringify(province) + '来自:' + source);
	var code, title;
	if (typeof info === 'object') {
		code = info.code;
		title = info.title;
	} else if (/^\d{2}$/.test(info)) {
		code = info;
		title = this.getTitleByCode(info);
	} else {
		code = this.getCodeByTitle(info);
		title = info;
	}
	if (!code || !+code) {
		return;
	}
	if (this.data && +code === +this.data.code && this.data.source !== 'ip') {
		return;
	}
	//如果设置的省份跟测算信息不一致 情况测算信息
	var estimate = estimateInfo.get();
	if (estimate && +code !== +estimate.province) {
		estimateInfo.clear('省份跟测算省份不一致');
	}
	// console.log({ code, title, source });
	var data = {
		code: code, title: title, source: source
	};
	this.emit(data);
	if (this.data) {
		location.href = location.pathname;
		// location.reload();
	}
	if (source !== 'ip') {
		var cookieData = { province: data.code, wenli: '', kemu: '', phone: '' };
		cookieObj.setCookie('user_tmp', 'think:' + JSON.stringify(cookieData));
	}
	this.data = data;
}
ProvinceContorl.prototype.get = function get() {
	return this.data
}
ProvinceContorl.prototype.logout = function logout() {
	var localeCode = localStorage.getItem('province-code');
	var userCode = localStorage.getItem('user-province-code');
	localStorage.removeItem('user-province-code');
	var code = userCode || localeCode;
	this.set(code, 'locale');
}
ProvinceContorl.prototype.getTitle = function getCode() {
	return this.data ? this.data.title : '';
}
ProvinceContorl.prototype.getCode = function getCode() {
	return this.data ? this.data.code : '';
}
ProvinceContorl.prototype.getSource = function () {
	return this.data ? this.data.source : '';
}
// ProvinceContorl.prototype.getUserCode = function getUserCode() {
// 	return localStorage.getItem('user-province-code');
// }
// ProvinceContorl.prototype.getLocaleCode = function getLocaleCode() {
// 	return localStorage.getItem('province-code');
// }
var provinceContorl = new ProvinceContorl();


// 判断分数是否低于专科分数线
function includes(list, item) {
	return list.indexOf(item) > -1;
}
var jzy_batchObj = {
	jsonstr: { "11": [{ "wenli": 3, "score": 120 }], "12": [{ "wenli": 3, "score": 120 }], "13": [{ "wenli": 1, "score": 220 }, { "wenli": 2, "score": 200 }], "14": [{ "wenli": 1, "score": 130 }, { "wenli": 2, "score": 130 }], "15": [{ "wenli": 1, "score": 160 }, { "wenli": 2, "score": 160 }], "21": [{ "wenli": 1, "score": 150 }, { "wenli": 2, "score": 150 }], "22": [{ "wenli": 1, "score": 130 }, { "wenli": 2, "score": 130 }], "23": [{ "wenli": 1, "score": 160 }, { "wenli": 2, "score": 160 }], "31": [{ "wenli": 3, "score": 143 }], "32": [{ "wenli": 1, "score": 230 }, { "wenli": 2, "score": 260 }], "34": [{ "wenli": 1, "score": 200 }, { "wenli": 2, "score": 200 }], "35": [{ "wenli": 1, "score": 220 }, { "wenli": 2, "score": 220 }], "36": [{ "wenli": 1, "score": 150 }, { "wenli": 2, "score": 150 }], "41": [{ "wenli": 1, "score": 180 }, { "wenli": 2, "score": 180 }], "42": [{ "wenli": 1, "score": 200 }, { "wenli": 2, "score": 200 }], "43": [{ "wenli": 1, "score": 200 }, { "wenli": 2, "score": 200 }], "44": [{ "wenli": 1, "score": 160 }, { "wenli": 2, "score": 160 }], "45": [{ "wenli": 1, "score": 180 }, { "wenli": 2, "score": 180 }], "46": [{ "wenli": 3, "score": 250 }], "50": [{ "wenli": 1, "score": 180 }, { "wenli": 2, "score": 180 }], "51": [{ "wenli": 1, "score": 150 }, { "wenli": 2, "score": 150 }], "52": [{ "wenli": 1, "score": 180 }, { "wenli": 2, "score": 180 }], "53": [{ "wenli": 1, "score": 200 }, { "wenli": 2, "score": 200 }], "61": [{ "wenli": 1, "score": 160 }, { "wenli": 2, "score": 160 }], "62": [{ "wenli": 1, "score": 160 }, { "wenli": 2, "score": 160 }], "63": [{ "wenli": 1, "score": 160 }, { "wenli": 2, "score": 160 }], "64": [{ "wenli": 1, "score": 150 }, { "wenli": 2, "score": 150 }], "65": [{ "wenli": 1, "score": 180 }, { "wenli": 2, "score": 180 }], "33": { "wenli": 3, "score": 279 }, "37": { "wenli": 3, "score": 150 } },
	veryscore: function (province, kemu, myscore) {
		var pro_3_3_list = [11, 12, 31, 33, 37, 46];
		var pro_3_2_list = [13, 21, 32, 35, 42, 43, 44, 50];
		if (includes(pro_3_3_list, province)) {
			kemu = '3';
		} else if (includes(pro_3_2_list, province)) {
			if (String(kemu).indexOf(2)) {
				kemu = '1';
			} else if (String(kemu).indexOf(4)) {
				kemu = '2';
			}
		}
		var scoreObj = jzy_batchObj.jsonstr[province];
		var i = 0;
		var proLine = 0;
		for (i = 0; i < scoreObj.length; i++) {
			if (scoreObj[i].wenli == kemu) {
				proLine = scoreObj[i].score;
				break;
			}
		}
		if (proLine == 0) {
			return { code: 0, 'msg': 'no data' };
		}
		if (proLine > myscore) {
			return { code: 1, 'msg': '最低分不能小于' + proLine + '分', proLine: proLine };
		}
		return { code: 0, 'msg': 'OK' };
	}
};
var vm = new Vue({
	el: document.getElementById('elemdom') ? '#elemdom' : '#js_header_dom',
	data: {
		province: provinceContorl.get(),
		cur_province_code: provinceContorl.getCode(),
		cur_province_title: provinceContorl.getTitle(),
		score: '',
		wenli: 2,
		isnew: 0,				// 是否新高考
		kemus: [],				// 新高考科目选择，当isnew=1时适用
		rank: '',
		isStartedTest: false
	},

	created: function () {
		var estimate = estimateInfo.getCache();
		if (estimate) {
			if (String(estimate.subjects).length === 1) {
				this.wenli = estimate.subjects;
			} else {
				this.kemus = estimate.subjects.split('').map(function (v) {
					return + v;
				});
			}
			this.score = estimate.score;
			this.rank = estimate.rank;
			this.cur_province_code = estimate.province;
			this.cur_province_title = provinceContorl.getTitleByCode(estimate.province);
		} else {
			//如果本次不存在测算信息 同步科目至面板
			if (window.userInfo) {
				var subjects = estimateInfo.getSubjectsByUser();
				if (subjects.length === 1) {
					this.wenli = subjects;
				} else {
					this.kemus = subjects.split('').map(function (v) {
						return + v;
					});
				}
			}

		}
		provinceContorl.on(this.handleProvinceChange);
		this.initHeaderSearch();
	},
	computed: {
		isnewgaokao: function () {
			// 北京、天津、上海、海南、山东、浙江（3+3）
			if ($.inArray(+this.cur_province_code, [11, 12, 31, 46, 37, 33]) > -1) {
				return 33;
			}
			// 湖北、福建、重庆、湖南、辽宁、河北、江苏、广东（1+2）
			if ($.inArray(+this.cur_province_code, [42, 35, 50, 43, 21, 13, 32, 44]) > -1) {
				return 12;
			}
			return 0;
		},
		computed_mykemustr: function () {
			this.kemus = this.kemus.sort();
			var kemuStrArr = [];
			$.each(this.kemus, function (i, v) {
				if (v == 1) {
					kemuStrArr.push('政治');
				}
				if (v == 2) {
					kemuStrArr.push('历史');
				}
				if (v == 3) {
					kemuStrArr.push('地理');
				}
				if (v == 4) {
					kemuStrArr.push('物理');
				}
				if (v == 5) {
					kemuStrArr.push('化学');
				}
				if (v == 6) {
					kemuStrArr.push('生物');
				}
				if (v == 7) {
					kemuStrArr.push('技术');
				}
			});
			var str = kemuStrArr.join('/');
			if (str == '') {
				str = '请选择';
			}
			return str;
		},
	},
	watch: {
		score: function (now, old) {
			if (String(old).length !== 3 && String(now).length === 3) {
				this.getRank();
			}
		}
	},
	methods: {
		initHeaderSearch: function () {
			this.$nextTick(function () {
				$("#header_search").click(function () {
					var kw = $.trim($("#header-search-wd").val() || '');
					if (!kw) {
						return layer.tips('请输入关键词查询', '#header-search-wd', {
							tips: 1
						});
					}
					location.href = '/search?kw=' + encodeURIComponent(kw);
				});
			});
		},
		handleProvinceChange: function (data) {
			var province = data.now;
			this.province = province;
			this.cur_province_code = province.code;
			this.cur_province_title = province.title;
			this.wenli = 2;
			this.kemus = [];
			// estimateInfo.clear();
			//如果省份来源于ip 并且 不是测算页 更新省份选择框信息
			if (province.source === 'ip') {
				if (location.pathname.indexOf('cesuan') === -1) {
					$('.set-student-province .stu-province-lists .default-province-title').text(this.cur_province_title);
					var btns = $('.set-student-province .stu-province-lists .btn-div');
					$.each(btns, function (i, v) {
						if ($(v).attr('title') == this.cur_province_title) {
							$(v).addClass('active');
							return false;
						}
					});
				}
			} else {
				var cookieData = { province: province.code, wenli: this.wenli, kemu: this.kemus.join(''), phone: '' };
				cookieObj.setCookie('user_tmp', 'think:' + JSON.stringify(cookieData));
			}
		},
		method_kemu: function (_val) {
			return ($.inArray(_val, this.kemus) > -1) ? true : false;
		},
		setProvince: function (province, source) {
			if (this.cur_province_code || source === 'user') {
				this.wenli = 2;
				this.kemus = [];
				// estimateInfo.clear();
				var cookieData = { province: province.code, wenli: this.wenli, kemu: this.kemus.join(''), phone: '' };
				// alert('设置cookie' + JSON.stringify(cookieData))
				cookieObj.setCookie('user_tmp', 'think:' + JSON.stringify(cookieData));
			}
			this.cur_province_code = +province.code;
			this.cur_province_title = province.title;

			provinceContorl.set(province, source);
		},
		getRank: function () {
			return
			var _this = this;
			$.ajax({
				methods: "get",
				url: '/index/chances/get_yfyd_by_score',
				json: true,
				data: { province: this.cur_province_code, score: this.score, wenli: this.isnewgaokao ? '3' : this.wenli },
				success: function (res) {
					var response = JSON.parse(res);
					if (response.data) {
						_this.rank = response.data;
						// alert(this.rank)
					}
					// console.log(res, typeof res, 'res');
				}
			})//
		},
		handleSubjectsChange: function () {
			this.rank = ''
		}
	},
	mounted: function () {
		// alert(1);
		if (this.$refs.header) {
			this.$refs.header.style.opacity = 1;
		}
	},
});

var siteobj = {
	// 绑定切换考生所在省单击事件
	stu_province: function () {
		$('.header .dropdown-content .list li').on('click', function () {
			var code = $(this).attr('code');
			provinceContorl.set(code, 'change');
			// location.reload();
		});
	}(),
	// 获取省份列表
	_getProvinceList: function () {
		var prvinces = '[{"code":11,"name":"北京"},{"code":12,"name":"天津"},{"code":13,"name":"河北"},{"code":14,"name":"山西"},{"code":15,"name":"内蒙"},{"code":21,"name":"辽宁"},{"code":22,"name":"吉林"},{"code":23,"name":"黑龙江"},{"code":31,"name":"上海"},{"code":32,"name":"江苏"},{"code":33,"name":"浙江"},{"code":34,"name":"安徽"},{"code":35,"name":"福建"},{"code":36,"name":"江西"},{"code":37,"name":"山东"},{"code":41,"name":"河南"},{"code":42,"name":"湖北"},{"code":43,"name":"湖南"},{"code":44,"name":"广东"},{"code":45,"name":"广西"},{"code":46,"name":"海南"},{"code":50,"name":"重庆"},{"code":51,"name":"四川"},{"code":52,"name":"贵州"},{"code":53,"name":"云南"},{"code":54,"name":"西藏"},{"code":61,"name":"陕西"},{"code":62,"name":"甘肃"},{"code":63,"name":"青海"},{"code":64,"name":"宁夏"},{"code":65,"name":"新疆"}]';
		return JSON.parse(prvinces);
	},
	// 根据省份code获取名称
	getProvinceTitle: function (code) {
		var prvinces = siteobj._getProvinceList();
		var title = '';
		$.each(prvinces, function (i, v) {
			if (v.code == code) {
				title = v.name;
			}
		});
		return title.replace('省', '').replace('市', '');
	},
	// 根据省份名称获取code
	getProvinceCode: function (title) {
		var prvinces = siteobj._getProvinceList();
		var code = '';
		$.each(prvinces, function (i, v) {
			if (v.name == title) {
				code = v.code;
			}
		});
		return code;
	},
	// 新高考科目
	kemus: function () {
		// var user_kemus = $.trim($('input[name="user_kemu"]').val());
		// if (user_kemus.length > 0) {
		// 	return;
		// }

		var layer_height = '220px';
		var html = '<div class="kemu-list" style="margin:15px 0">\
						<div class="label" style="display:flex;justify-content:space-between;align-items:center"><span>首选科目</span><button class="layui-btn layui-btn-sm layui-btn-danger" onclick="siteobj.chkkemuok()" style="height:25px;line-height:25px">确定</button></div>\
						<div class="btns">\
							<button class="layui-btn layui-btn-xs layui-btn-primary" onclick="siteobj.clickkemuFirst(this)" kemu="4">物理</button>\
							<button class="layui-btn layui-btn-xs layui-btn-primary" onclick="siteobj.clickkemuFirst(this)" kemu="2">历史</button></div>\
						<div class="label">再选科目</div>\
						<div class="btns">\
							<button class="layui-btn layui-btn-xs layui-btn-primary" onclick="siteobj.clickkemu(this)" kemu="5">化学</button>\
							<button class="layui-btn layui-btn-xs layui-btn-primary" onclick="siteobj.clickkemu(this)" kemu="6">生物</button>\
							<button class="layui-btn layui-btn-xs layui-btn-primary" onclick="siteobj.clickkemu(this)" kemu="1">政治</button>\
							<button class="layui-btn layui-btn-xs layui-btn-primary" onclick="siteobj.clickkemu(this)" kemu="3">地理</button>';
		html += '</div>'
		// 			<div class="btn-group">\
		// 				<button class="layui-btn layui-btn-sm layui-btn-primary" onclick="layer.closeAll()">取消</button><button class="layui-btn layui-btn-sm layui-btn-danger" onclick="siteobj.chkkemuok()">确定</button>\
		// 			</div>';
		// html += '</div>';
		if (vm.isnewgaokao == 33) {
			layer_height = '150px';
			var html = '<div class="kemu-list" style="margin:15px 0">\
						<div class="label"  style="display:flex;justify-content:space-between;align-items:center"><span>选择科目</span><button class="layui-btn layui-btn-sm layui-btn-danger" onclick="siteobj.chkkemuok()" style="height:25px;line-height:25px">确定</button></div>\
						<div class="btns" style="display:flex;flex-wrap:wrap;justify-content:center">\
							<button style="padding: 0px 15px;" class="layui-btn layui-btn-xs layui-btn-primary" onclick="siteobj.clickkemu(this)" kemu="4">物理</button>\
							<button style="padding: 0px 15px;"  class="layui-btn layui-btn-xs layui-btn-primary" onclick="siteobj.clickkemu(this)" kemu="2">历史</button>\
							<button style="padding: 0px 15px;"  class="layui-btn layui-btn-xs layui-btn-primary" onclick="siteobj.clickkemu(this)" kemu="5">化学</button>\
							<button style="padding: 0px 15px;"  class="layui-btn layui-btn-xs layui-btn-primary" onclick="siteobj.clickkemu(this)" kemu="6">生物</button>\
							<button style="padding: 0px 15px;"  class="layui-btn layui-btn-xs layui-btn-primary" onclick="siteobj.clickkemu(this)" kemu="1">政治</button>\
							<button style="padding: 0px 15px;"  class="layui-btn layui-btn-xs layui-btn-primary" onclick="siteobj.clickkemu(this)" kemu="3">地理</button>';
			if (vm.cur_province_code == 33) {
				layer_height = '190px';
				html += '<button class="layui-btn layui-btn-xs layui-btn-primary" onclick="siteobj.clickkemu(this)" kemu="7">技术</button>';
			}
			html += '</div>'
			// 			<div class="btn-group">\
			// 				<button class="layui-btn layui-btn-sm layui-btn-primary" onclick="layer.closeAll()">取消</button><button class="layui-btn layui-btn-sm layui-btn-danger" onclick="siteobj.chkkemuok()">确定</button>\
			// 			</div>';
			// html += '</div>';
		}
		// 坐标
		var X = $('.ana-box').offset().top + 160;
		var Y = $('.ana-box').offset().left + 25;
		layer.open({
			type: 1,
			title: false,
			closeBtn: 0,
			shadeClose: true,
			shade: 0.001,
			area: ['250px', layer_height],
			skin: 'skin-kemus',
			offset: [X, Y],
			content: html,
			success: function () {
				$('#gaokaokemus').html('');
				var btns = $('.skin-kemus .kemu-list button');
				$.each(btns, function (i, v) {
					var kemu = parseInt($(v).attr('kemu'));
					if ($.inArray(kemu, vm.kemus) > -1) {
						$(v).addClass('layui-btn-danger').removeClass('layui-btn-primary');
					}
				});
				// 锚定
				var elm = $('.skin-kemus');
				if ($(elm).offset() == undefined) {
					return;
				}
				$.event.add(window, "scroll", function () {
					var js_gkkm_position = $('.js-gkkm').offset().top + 35;
					$(elm).css('top', js_gkkm_position).css('position', 'absolute');
				});
			}
		});
	},
	// 选择文理科
	clickwenli: function (obj) {
		// var phone = $.trim($('input[name="user_phone"]').val());
		// var wenli = parseInt($('input[name="user_wenli"]').val());
		// if (phone && wenli > 0) {
		// 	return;
		// }
		vm.wenli = parseInt($(obj).attr('wenli'));
		vm.handleSubjectsChange();
	},
	// 首选科目
	clickkemuFirst: function (obj) {
		var kemu = parseInt($(obj).attr('kemu'));
		$(obj).removeClass('layui-btn-primary').addClass('layui-btn-danger').siblings('button').removeClass('layui-btn-danger').addClass('layui-btn-primary');
		$.each([2, 4], function (i, v) {
			var i = $.inArray(v, vm.kemus);
			if (i > -1) {
				vm.kemus.splice(i, 1);
			}
		});
		vm.kemus.push(kemu);
		vm.handleSubjectsChange();
	},
	// 选择科目
	clickkemu: function (obj) {
		var kemu = parseInt($(obj).attr('kemu'));
		var i = $.inArray(kemu, vm.kemus);
		if (i > -1) {
			vm.kemus.splice(i, 1);
			vm.handleSubjectsChange();
			$(obj).removeClass('layui-btn-danger').addClass('layui-btn-primary');
		}
		if (i == -1) {
			if (vm.kemus.length >= 3) {
				return layer.msg('最多选择3个科目', { icon: 2, time: 3000 });
			}
			vm.kemus.push(kemu);
			vm.handleSubjectsChange();
			$(obj).removeClass('layui-btn-primary').addClass('layui-btn-danger');
		}
	},
	// 科目选择完成
	chkkemuok: function () {
		if (vm.kemus.length != 3) {
			return layer.msg('请选择3个科目', { icon: 2, time: 3000 });
		}
		layer.closeAll();
	},
	// 未登录设置默认省份
	setStudentProvince: function (obj) {
		console.log(obj, 'obj')
		var code = $(obj).attr('code');
		// vm.setProvince({
		// 	code,
		// 	title: siteobj.getProvinceTitle(code)
		// }, 'locale');
		// var cookieData = { province: vm.cur_province_code, wenli: vm.wenli, kemu: vm.kemu, phone: '' };
		// cookieObj.setCookie('user_tmp', 'think:' + JSON.stringify(cookieData));
		provinceContorl.set($(obj).attr('code'), 'change');
		layer.closeAll();
	}
}



function init() {
	var data = {};
	data.user_phone = $.trim($('input[name="user_phone"]').val());
	data.user_openid = $.trim($('input[name="user_openid"]').val());
	// data.user_provicne = parseInt($.trim($('input[name="user_province"]').val()));
	// var user_cookie_province = $.trim($('input[name="user_cookie_province"]').val());
	// data.wenli = parseInt($('input[name="user_wenli"]').val());
	// data.kemu = parseInt($('input[name="user_kemu"]').val());
	//console.log(data);
	// 用户未登录，未设置省份、科目 测算页不弹选择省份弹窗
	var province = provinceContorl.get();
	var source = province ? province.source : ''
	if (!data.user_phone && (source === 'ip' || !source) && location.pathname.indexOf('cesuan') === -1) {
		$.get('/index/index/setStudentProvince', {}, function (res) {
			layer.open({
				type: 1,
				title: false,
				closeBtn: 0,
				shadeClose: false,
				shade: 0.5,
				offset: '200px',
				skin: 'set-student-province',
				area: ['590px', '450px'],
				content: res.data,
				success: function () {
					$('.set-student-province .stu-province-lists .default-province-title').text(vm.cur_province_title);
					var btns = $('.set-student-province .stu-province-lists .btn-div');
					$.each(btns, function (i, v) {
						if ($(v).attr('title') == vm.cur_province_title) {
							$(v).addClass('active');
							return false;
						}
					});
					bodyHidden();
				}
			});
		}, 'json');
		return;
	}
	// 用户是微信登录 未绑定手机号
	if (data.user_openid && !data.user_phone) {
		layer.open({
			type: 2,
			title: false,
			closeBtn: 0,
			shade: 0.3,
			offset: '200px',
			area: ['500px', '500px'],//['450px', '390px']
			content: '/index/index/bindPhone',
			success: function (layero, index) {
				var iframeWin = window[layero.find('iframe')[0]['name']];
				iframeWin.init(index);
			}
		});
		return;
	}
	// 用户已登录，未设置省份、科目 测算页不弹选择省份弹窗
	if (data.user_phone > 0 && !(province && province.source === 'user') && location.pathname.indexOf('cesuan') === -1) {
		layer.open({
			type: 2,
			title: false,
			closeBtn: 0,
			shade: 0.3,
			offset: '200px',
			area: ['492px', '390px'],//['450px', '390px']
			content: '/index/index/setUserProvinceKemus',
			success: function (layero, index) {
				var iframeWin = window[layero.find('iframe')[0]['name']];
				iframeWin.init(index);
				bodyHidden();
			}
		});
		return;
	}
}
layui.use(['layer', 'carousel', 'flow'], function () {
	carousel = layui.carousel;
	carousel.render({
		elem: '#carousels'
		, width: '100%'
		, height: '400px'
		, arrow: 'none'
	});
	var flow = layui.flow;
	flow.lazyimg();

	$(".animate-box").hover(function () {
		$(this).addClass('animated rotateIn');
	}, function () {
		$(this).removeClass('animated rotateIn');
	});
	init();
	// var user_provicne = $.trim($('input[name="user_province"]').val());
	// var user_cookie_provicne = $.trim($('input[name="user_cookie_province"]').val());
	// console.log({ user_provicne, user_cookie_provicne })
	// if (+user_provicne) {
	// 	vm.setProvince({
	// 		code: user_provicne,
	// 		title: siteobj.getProvinceTitle(user_provicne)
	// 	}, 'user');
	// 	setTimeout(function () { init(); }, 50);
	// } else if (+user_cookie_provicne) {
	// 	vm.setProvince({
	// 		code: user_cookie_provicne,
	// 		title: siteobj.getProvinceTitle(user_cookie_provicne)
	// 	}, 'locale');
	// 	setTimeout(function () { init(); }, 50);
	// } else {
	// 	$.getScript('https://ip.ws.126.net/ipquery', function () {
	// 		lo = lo.replace('省', '').replace('市', '');
	// 		vm.setProvince({
	// 			code: siteobj.getProvinceCode(lo),
	// 			title: siteobj.getProvinceTitle(siteobj.getProvinceCode(lo))
	// 		}, 'locale');
	// 		setTimeout(function () { init(); }, 50);
	// 	});
	// }
	// 锚定
	anchoringNav();
});

function bodyHidden() {
	var topPos = $(document).scrollTop();
	$('document').css('overflow', 'hidden');
	$('body').css({
		'position': 'fixed',
		'top': -topPos + 'px',
		'width': '100%'
	});
}

// 锚定
function anchoringNav() {
	var elm = $('#elemdom .header');
	if ($(elm).offset() == undefined) {
		return;
	}
	var startPos = $(elm).offset().top;
	$.event.add(window, "scroll", function () {
		if ($(window).scrollTop() > (startPos + 150)) {
			$(elm).css('position', 'fixed').css('top', '0px').css('width', '100%').css('box-shadow', '0 4px 8px 0 rgba(7, 17, 27, 0.1)').css('opacity', 0.9).css('z-index', 29991016);
			//$(elm).animate({position:'fixed',top:'0px',width:'100%'},100);
		} else {
			$(elm).css('position', 'relative').css('box-shadow', 'none').css('opacity', 1);
		}
	});
}

// IP定位
function ip_pos(callback) {
	$.getScript('https://ip.ws.126.net/ipquery?ip', function () {
		if (callback != undefined) {
			callback(lo);
		}
	});
}

// 选择科目+分数 准备测算
function requestSetEstimateInfo(canClose, onConfirm) {
	window.onEstimateInfoConfirm = onConfirm;
	if (window.ins) {
		window.ins.initSubjects();
	}
	// if (hint) {
	// 	$('.examination-confirm-hint').html(hint);
	// }
	$("#examinationDialog").removeClass('layui-hide');
	if (canClose === false) {
		$("#examinationDialog .layui-icon-close").hide();
	}
	var topPos = $(document).scrollTop();
	$('document').css('overflow', 'hidden');
	$('body').css({
		'position': 'fixed',
		'top': -topPos + 'px',
		'width': '100%'
	});
}

function qsStringify(data) {
	var s = [];
	for (var key in data) {
		s.push(key + '=' + data[key]);
	}
	return s.join('&')
}
function goToEstimate(data) {
	//data {province,score,rank,subjects}
	try {
		// estimateInfo.set({
		// 	province: data.province,
		// 	rank: data.rank,
		// 	score: data.score,
		// 	subjects: data.subjects,
		// 	source: 'change'
		// });
		window.location.href = '/cesuan?' + qsStringify(data);
	} catch (e) {
		console.log(e, 'e');
		//
	}
}