webpackJsonp([0],{

/***/ 311:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
    value: true
});

var _demo = __webpack_require__(763);

var _demo2 = _interopRequireDefault(_demo);

var _demo3 = __webpack_require__(764);

var _demo4 = _interopRequireDefault(_demo3);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

/*{
    system_name: '', //系统名称
    process_id: '', //流程id
    process_info: {
        desc: '', //流程描述
        images: [  //流程相关图片
            {
                thumb_url: '', //缩略图url
                url: '', //原图url
                name: '' //图片名称
            }
        ],
        users: { //参与流程的用户
            history : [ //发起和历史审批过该流程的用户
                {
                    id: '',
                    user_name: '',
                    user_phone: '',
                    activity: '' //当前流程的角色
                }
            ],
            todo : [ //将要进行当前步骤审批的用户
                {
                    id: '',
                    user_name: '',
                    user_phone: '',
                    activity: '' //当前流程的角色
                }
            ]
        }
    },
    current_user: { //当前登录用户信息
        id: '',
        user_name: '',
        user_phone: ''
    }
}*/
exports.default = {
    "billImage": _demo2.default,
    "billImage_big": _demo2.default,
    "processImage": _demo4.default,
    "processImage_big": _demo4.default,
    "currentuser": {
        "id": "1001WW10000000000540",
        "name": "101renyuan"
    },
    "users": {
        "history": [{
            "activity": "制单人<制单>",
            "cuserid": "1001WW10000000000540",
            "user_email": "",
            "user_name": "101renyuan",
            "user_phone": "15110087666",
            "usercode": "101"
        }, {
            "activity": "001<加签改派>",
            "cuserid": "1001WW10000000000546",
            "user_email": "",
            "user_name": "导入测试用户001",
            "user_phone": "15110087666",
            "usercode": "yonyou001"
        }],
        "todo": [{
            "activity": "业务领导审批",
            "cuserid": "1001WW1000000000057M",
            "user_email": "",
            "user_name": "导入测试用户023",
            "user_phone": "15110087666",
            "usercode": "yonyou023"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW1000000000057G",
            "user_email": "",
            "user_name": "导入测试用户022",
            "user_phone": "15110087666",
            "usercode": "yonyou022"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW1000000000053I",
            "user_email": "",
            "user_name": "导入测试用户013",
            "user_phone": "15110087666",
            "usercode": "yonyou013"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW1000000000057A",
            "user_email": "",
            "user_name": "导入测试用户021",
            "user_phone": "15110087666",
            "usercode": "yonyou021"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW1000000000052M",
            "user_email": "",
            "user_name": "导入测试用户008",
            "user_phone": "15110087666",
            "usercode": "yonyou008"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW1000000000052U",
            "user_email": "",
            "user_name": "导入测试用户009",
            "user_phone": "15110087666",
            "usercode": "yonyou009"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW10000000000536",
            "user_email": "",
            "user_name": "导入测试用户011",
            "user_phone": "15110087666",
            "usercode": "yonyou011"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW10000000000530",
            "user_email": "",
            "user_name": "导入测试用户010",
            "user_phone": "15110087666",
            "usercode": "yonyou010"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW1000000000054U",
            "user_email": "",
            "user_name": "导入测试用户005",
            "user_phone": "15110087666",
            "usercode": "yonyou005"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW10000000000556",
            "user_email": "",
            "user_name": "导入测试用户007",
            "user_phone": "15110087666",
            "usercode": "yonyou007"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW10000000000574",
            "user_email": "",
            "user_name": "导入测试用户020",
            "user_phone": "15110087666",
            "usercode": "yonyou020"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW1000000000056G",
            "user_email": "",
            "user_name": "导入测试用户018",
            "user_phone": "15110087666",
            "usercode": "yonyou018"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW1000000000054C",
            "user_email": "",
            "user_name": "导入测试用户002",
            "user_phone": "15110087666",
            "usercode": "yonyou002"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW1000000000054O",
            "user_email": "",
            "user_name": "导入测试用户004",
            "user_phone": "15110087666",
            "usercode": "yonyou004"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW10000000000550",
            "user_email": "",
            "user_name": "导入测试用户006",
            "user_phone": "15110087666",
            "usercode": "yonyou006"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW1000000000056M",
            "user_email": "",
            "user_name": "导入测试用户019",
            "user_phone": "15110087666",
            "usercode": "yonyou019"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW1000000000054I",
            "user_email": "",
            "user_name": "导入测试用户003",
            "user_phone": "15110087666",
            "usercode": "yonyou003"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW10000000000564",
            "user_email": "",
            "user_name": "导入测试用户016",
            "user_phone": "15110087666",
            "usercode": "yonyou016"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW1000000000055S",
            "user_email": "",
            "user_name": "导入测试用户014",
            "user_phone": "15110087666",
            "usercode": "yonyou014"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW10000000000546",
            "user_email": "",
            "user_name": "导入测试用户001",
            "user_phone": "15110087666",
            "usercode": "yonyou001"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW10000000000540",
            "user_email": "",
            "user_name": "101renyuan",
            "user_phone": "15110087666",
            "usercode": "101"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW1000000000055Y",
            "user_email": "",
            "user_name": "导入测试用户015",
            "user_phone": "15110087666",
            "usercode": "yonyou015"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW1000000000057Y",
            "user_email": "",
            "user_name": "导入测试用户025",
            "user_phone": "15110087666",
            "usercode": "yonyou025"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW1000000000057S",
            "user_email": "",
            "user_name": "导入测试用户024",
            "user_phone": "15110087666",
            "usercode": "yonyou024"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW10000000000584",
            "user_email": "",
            "user_name": "导入测试用户026",
            "user_phone": "15110087666",
            "usercode": "yonyou026"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW1000000000056A",
            "user_email": "",
            "user_name": "导入测试用户017",
            "user_phone": "15110087666",
            "usercode": "yonyou017"
        }, {
            "activity": "业务领导审批",
            "cuserid": "1001WW1000000000053C",
            "user_email": "",
            "user_name": "导入测试用户012",
            "user_phone": "15110087666",
            "usercode": "yonyou012"
        }]
    },
    "billPk": "0001WP1000000000F84G",
    "billMessage": "制单人:101renyuan,单据日期:2017-07-03 00:00:00金额:123.00"
};

/***/ }),

/***/ 763:
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "demo.png?173f328";

/***/ }),

/***/ 764:
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "demo2.png?d94378e";

/***/ })

});