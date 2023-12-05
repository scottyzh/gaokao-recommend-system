layui.use(['jquery', 'layer'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var _provinceList = [{ "code": 34, "name": "安徽" }, { "code": 11, "name": "北京" }, { "code": 50, "name": "重庆" }, { "code": 35, "name": "福建" }, { "code": 62, "name": "甘肃" }, { "code": 44, "name": "广东" }, { "code": 45, "name": "广西" }, { "code": 52, "name": "贵州" }, { "code": 46, "name": "海南" }, { "code": 13, "name": "河北" }, { "code": 41, "name": "河南" }, { "code": 23, "name": "黑龙江" }, { "code": 42, "name": "湖北" }, { "code": 43, "name": "湖南" }, { "code": 36, "name": "江西" }, { "code": 22, "name": "吉林" }, { "code": 32, "name": "江苏" }, { "code": 21, "name": "辽宁" }, { "code": 15, "name": "内蒙古" }, { "code": 64, "name": "宁夏" }, { "code": 63, "name": "青海" }, { "code": 37, "name": "山东" }, { "code": 14, "name": "山西" }, { "code": 61, "name": "陕西" }, { "code": 31, "name": "上海" }, { "code": 51, "name": "四川" }, { "code": 12, "name": "天津" }, { "code": 65, "name": "新疆" }, { "code": 53, "name": "云南" }, { "code": 33, "name": "浙江" }];
    var _province = $("#alert-dialog #dialog_user_exam_province").val() || '';
    var _wenli = $("#alert-dialog #dialog_user_exam_wenli").val() || '';
    var _kemu = $("#alert-dialog #dialog_user_exam_kemu").val() || '';
    var _score = $("#alert-dialog #dialog_user_exam_score").val() || '';
    var _rank = $("#alert-dialog #dialog_user_exam_rank").val() || '';
    var _phone = $("#alert-dialog #dialog_user_exam_phone").val() || '';
    var _getProvinceName = function (code) {
        var _name = '安徽';
        for (var i in _provinceList) {
            if (_provinceList[i].code == code) {
                _name = _provinceList[i].name;
                break;
            }
        }
        return _name;
    };
    var kemu2Array = function (kemu) {
        if (kemu.length <= 0) {
            return [];
        }
        var _temp = kemu.split('');
        var _data = [];
        for (var i in _temp) {
            _data.push(parseInt(_temp[i]));
        }
        return _data;
    };
    var province = provinceContorl.get();
    // console.log(JSON.stringify(province), 'province');
    // provinceContorl.on(({ now }) => {
    //     if (ins) {
    //         var province = now;
    //         ins.code = province.code;
    //         alert(9)
    //         ins.province = province.title;
    //         ins.score = '';
    //         ins.rank = '';
    //         ins.wenli = 1;
    //         ins.kemus = [];
    //         ins.makeKemuHtml(province.code);
    //         ins.initSubjects();
    //     }
    // })
    var ins = new Vue({
        el: '#alert-dialog',
        data: {
            visible: true,
            prvinces: [],
            code: province ? province.code : '',
            showProvince: 1,
            province: province ? province.title : '',
            isNew: 0, //是否新高考,1为是
            wenli: _wenli ? _wenli : '', //文理
            kemus: kemu2Array(_kemu),// 新高考科目选择，当isnew=1时适用
            score: _score > 0 ? _score : '',
            rank: _rank > 0 ? _rank : '',
            xgkType: 0,
        },
        created: function () {
            // alert('回显vm测算信息')
            this.prvinces = _provinceList;
            window.clcikRoom = this.clcikRoom;
            this.makeKemuHtml(this.code);
            this.initSubjects();
        },
        computed: {
            /**
             * 同时监听下面这些数据
             * @returns {{score: *, wenli: *, kemus: *, code: *, province: *}}
             */
            getRankEvent: function () {
                return {
                    score: this.score,
                    code: this.code,
                    wenli: this.wenli,
                    kemus: this.kemus,
                }
            },
        },
        watch: {
            getRankEvent: function (nv) {
                if (nv.code > 0 && nv.score > 0 && (nv.wenli > 0 || nv.kemus.length > 0)) {
                    ins.getRank();
                } else {
                    ins.rank = '';
                }
            }
        },
        methods: {
            initSubjects: function () {
                var code = this.code;
                var provinceSource = provinceContorl.getSource();
                var estimate = estimateInfo.getCache();
                // console.log({ estimate, code })
                // +estimate.province === +code
                if (estimate) {
                    if (provinceSource === 'user' && +code !== +estimate.province) {
                        return
                    }
                    this.code = estimate.province;
                    this.province = provinceContorl.getTitleByCode(this.code);
                    this.score = estimate.score;
                    this.rank = estimate.rank;
                    this.wenli = estimate.wenli;
                    this.kemus = estimate.mykemuList;
                    this.isNew = estimate.mykemuList.length > 1;
                    this.makeKemuHtml(this.code);
                }
            },
            getRank: function () {
                return;
                if (this.rank || String(this.score).length !== 3) {
                    return;
                }
                var me = this;
                var _new_wenli = me.wenli;
                var returnCode = isInArray(me.code);
                if (returnCode == 33) {
                    _new_wenli = 3;
                } else if (returnCode == 12) {
                    var is2 = me.kemus.indexOf(2) > -1 ? 1 : 0;
                    var is4 = me.kemus.indexOf(4) > -1 ? 1 : 0;
                    if (is2 + is4 != 1) {
                        return false;
                    }
                    _new_wenli = is2 ? 12 : 14;
                }

                $.ajax({
                    url: '/index/chances/get_yfyd_by_score',
                    type: 'GET',
                    data: {
                        province: me.code,
                        score: me.score,
                        wenli: _new_wenli
                    },
                    dataType: 'json',
                    success: function (ret) {
                        if (ret.code == 0 && ret.data > 0) {
                            me.rank = ret.data;
                        }
                    }
                });
            },
            //点击省份
            clickProvince: function (index) {
                this.code = index;
                $(".cityHide").fadeOut(100);
                this.showProvince = 1;
                this.wenli = 1;
                this.kemus = [];
                this.score = '';
                this.rank = '';
                var prvinces = this.prvinces;
                for (var i in prvinces) {
                    if (this.code == prvinces[i].code) {
                        this.province = prvinces[i].name
                    }
                }
                this.makeKemuHtml(this.code);
            },

            makeKemuHtml: function (province_code) {
                this.xgkType = isInArray(province_code);
                if (this.xgkType == 33) {
                    this.isNew = 1;
                    this.wenli = ' ';
                    var html = '<div class="flex_between2 examination_li">\
                             <p class="examinationLi-left" style="margin-top:7px">选择科目</p>\
                             <div class="examinationLi-list">\
                             <p class="sxKemu1 ' + (this.kemus.indexOf(4) > -1 ? 'examination_active' : '') + '"  kemu="4" >物理</p>\
                             <p class="sxKemu1 ' + (this.kemus.indexOf(2) > -1 ? 'examination_active' : '') + '"  kemu="2">历史</p>\
                             <p class="sxKemu1 ' + (this.kemus.indexOf(5) > -1 ? 'examination_active' : '') + '"  kemu="5">化学</p>\
                             <p class="sxKemu1 ' + (this.kemus.indexOf(6) > -1 ? 'examination_active' : '') + '"  kemu="6">生物</p>\
                             <p class="sxKemu1 ' + (this.kemus.indexOf(1) > -1 ? 'examination_active' : '') + '"  kemu="1">政治</p>\
                             <p class="sxKemu1 ' + (this.kemus.indexOf(3) > -1 ? 'examination_active' : '') + '"  kemu="3">地理</p>';
                    if (this.code == 33) {
                        html += '<p class="sxKemu1 ' + (this.kemus.indexOf(7) > -1 ? 'examination_active' : '') + '"  kemu="7">技术</p>';
                    }

                    html += '</div>';
                    $('#addHtml').html(html)
                } else if (this.xgkType == 12) {
                    this.isNew = 1;
                    this.wenli = ' ';
                    var html = '<div class="flex_between2 examination_li">\
                                <p class="examinationLi-left" style="margin-top:7px">首选科目</p>\
                                <div class="examinationLi-list">\
                                    <p class="sxKemuFirst ' + (this.kemus.indexOf(4) > -1 ? 'examination_active' : '') + '" kemu="4">物理</p>\
                                    <p class="sxKemuFirst ' + (this.kemus.indexOf(2) > -1 ? 'examination_active' : '') + '" kemu="2">历史</p>\
                                </div>\
                            </div>\
                            <div class="flex_between2 examination_li">\
                                <p class="examinationLi-left" style="margin-top:7px">再选科目</p>\
                                <div class="examinationLi-list">\
                                    <p class="sxKemu1 ' + (this.kemus.indexOf(5) > -1 ? 'examination_active' : '') + '" kemu="5">化学</p>\
                                    <p class="sxKemu1 ' + (this.kemus.indexOf(6) > -1 ? 'examination_active' : '') + '" kemu="6">生物</p>\
                                    <p class="sxKemu1 ' + (this.kemus.indexOf(1) > -1 ? 'examination_active' : '') + '" kemu="1">政治</p>\
                                    <p class="sxKemu1 ' + (this.kemus.indexOf(3) > -1 ? 'examination_active' : '') + '" kemu="3">地理</p>\
                                </div>\
                            </div>';
                    $('#addHtml').html(html)
                } else {
                    this.isNew = 0;
                    $('#addHtml').html('')
                }
            },
            //点击出现省份框
            clickShowPro: function () {
                if (this.showProvince == 1) {
                    $(".cityHide").fadeIn(100);
                    this.showProvince = 2
                } else {
                    $(".cityHide").fadeOut(100);
                    this.showProvince = 1
                }
            },
            handleSubjectsChange: function () {
                // this.score = '';
                this.rank = '';
            },
            //点击高考科目
            clickGk: function (index) {
                this.wenli = index;
                this.handleSubjectsChange();
            },
            //点击确定
            clickTrue: function (index) {
                this.visible = true;
            },
        }
    });
    //如果当本地不存在数据时
    if (!province) {
        //监听省份的改变 同步至弹窗
        provinceContorl.on(function (data) {
            if (!data.old && data.now) {
                if (!ins.code) {
                    ins.code = data.now.code;
                    ins.province = data.now.title;
                }
            }
        });
    }
    window.ins = ins;
    $('#addHtml').on("click", ".sxKemu1", function () {

        var kemu = parseInt($(this).attr('kemu'));
        var i = $.inArray(kemu, ins.kemus);
        if (i > -1) {
            ins.handleSubjectsChange();
            ins.kemus.splice(i, 1);
            $(this).removeClass('examination_active').addClass('theNew');
        }
        if (i == -1) {
            if (ins.kemus.length >= 3) {
                return layer.msg('最多选择3个科目', { icon: 2 });
            }
            ins.handleSubjectsChange();
            ins.kemus.push(kemu);
            $(this).removeClass('theNew').addClass('examination_active');
        }
    })
    //点击首选科目
    $("#addHtml").on('click', '.sxKemuFirst', function (obj) {
        $('.sxKemuFirst').each(function () {
            $(this).removeClass('examination_active');
        });
        $(this).addClass('examination_active');
        var kemu = parseInt($(this).attr('kemu'));
        $.each([2, 4], function (i, v) {
            var i = $.inArray(v, ins.kemus);
            if (i > -1) {
                ins.kemus.splice(i, 1);
            }
        });
        ins.score = '';
        ins.rank = '';
        ins.kemus.push(kemu);
    });
    //点击再选科目
    $('#addHtml').on("click", ".zxKemu", function () {
        var kemu = parseInt($(this).attr('kemu'));
        var i = $.inArray(kemu, ins.kemus);
        if (i > -1) {
            ins.handleSubjectsChange();
            ins.kemus.splice(i, 1);
            $(this).removeClass('examination_active').addClass('theNew');
        }
        if (i == -1) {
            if (ins.kemus.length >= 2) {
                return layer.msg('最多选择2个科目', { icon: 2 });
            }
            ins.handleSubjectsChange();
            ins.kemus.push(kemu);
            $(this).removeClass('theNew').addClass('examination_active');
        }
    })
    //点击清空
    $('.UniversityDialog').on("click", ".layui-icon-close", function () {
        $("#examinationDialog").addClass('layui-hide');
        $('document').css('overflow', 'scroll');
        $('body').css('position', 'static');
    })
    //点击确定
    $(".UniversityDialog").on('click', '.examination_btn', function () {
        var code = ins.code,
            rank = ins.rank,
            score = ins.score,
            wenli = ins.wenli,
            kemus = ins.kemus;

        if (!code) {
            return layer.msg('请选择省份', { icon: 2, time: 3000 });
        }
        var subjects = +wenli ? wenli : kemus.join('');
        // console.log({ code, subjects, wenli, kemus })
        if (!estimateInfo.isSubjectValid(code, subjects)) {
            return layer.msg('请选择科目', { icon: 2, time: 3000 });
        }
        var verifyMinScore = jzy_batchObj.veryscore(provinceContorl.getCode(), subjects, score);
        if (verifyMinScore.code === 1) {
            return layer.msg(verifyMinScore.msg, { icon: 5, time: 3000 });
        }
        if (score > 750) {
            // return layer.alert('请输入200~700间的分数',{icon:0});
            return layer.msg('最高分不能大于750分', { icon: 5, time: 3000 });
        }
        // if (!rank) {
        //     return layer.msg('请输入高考排名', {icon: 2,time: 3000 });
        // }
        estimateInfo.set({
            province: code,
            rank: rank,
            score: score,
            subjects: subjects,
            source: 'change'
        });
        window.$_dialog_user_exam = {
            province: ins.code,
            provinceName: ins.province,
            wenli: ins.wenli,
            kemus: ins.kemus,
            xgkType: ins.xgkType,
        };
        if (window.onEstimateInfoConfirm) {
            const close = window.onEstimateInfoConfirm();
            if (close === false) {
                return;
            }
        } else {
            window.location.href = '/cesuan'
        }
        $("#examinationDialog").addClass('layui-hide');
        $('document').css('overflow', 'scroll');
        $('body').css('position', 'static');

        console.log(ins.$data, 'ins');
    });


    function isInArray(value) {
        if ($.inArray(+value, [11, 12, 31, 46, 37, 33]) > -1) {
            return 33;
        }
        // 湖北、福建、重庆、湖南、辽宁、河北、江苏、广东（1+2）
        if ($.inArray(+value, [42, 35, 50, 43, 21, 13, 32, 44]) > -1) {
            return 12;
        }
        return 0;
    }
});
function closeExaminationDialog() {
    $("#examinationDialog").addClass('layui-hide');
    $('document').css('overflow', 'scroll');
    $('body').css('position', 'static');
}