(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-f3498b32"],{1341:function(t,n,e){"use strict";e.r(n);var a=function(){var t=this,n=t.$createElement,e=t._self._c||n;return e("div",{staticClass:"sub-container"},[e("ul",{staticClass:"tabs sub"},[e("li",{on:{click:function(n){return t.goto("/admin/youtube")}}},[t._v("유튜브")]),e("li",{on:{click:function(n){return t.goto("/admin/column")}}},[t._v(" 칼럼 ")]),e("li",{staticClass:"current",on:{click:function(n){return t.goto("/admin/writer")}}},[t._v("칼럼 작성자")])]),e("div",{staticClass:"contents-container"},[e("p",{staticClass:"contents-title underline marB-20 body3"},[t._v("칼럼 작성자 등록")]),e("div",{staticClass:"input-container marB-40"},[e("section",{staticClass:"basic-info"},[e("div",{staticClass:"input-item"},[e("p",{staticClass:"small-title"},[t._v("이름")]),e("input",{directives:[{name:"model",rawName:"v-model",value:t.name,expression:"name"}],staticClass:"admin-input small",attrs:{type:"text",name:"name",placeholder:"이름"},domProps:{value:t.name},on:{input:function(n){n.target.composing||(t.name=n.target.value)}}})]),e("div",{staticClass:"input-item"},[e("p",{staticClass:"small-title"},[t._v("직업")]),e("input",{directives:[{name:"model",rawName:"v-model",value:t.job,expression:"job"}],staticClass:"admin-input small",attrs:{type:"text",name:"job",placeholder:"직업"},domProps:{value:t.job},on:{input:function(n){n.target.composing||(t.job=n.target.value)}}})]),e("div",{staticClass:"input-item"},[e("p",{staticClass:"small-title"},[t._v("상세 설명")]),e("input",{directives:[{name:"model",rawName:"v-model",value:t.contents,expression:"contents"}],staticClass:"admin-input large",attrs:{type:"text",name:"contents"},domProps:{value:t.contents},on:{input:function(n){n.target.composing||(t.contents=n.target.value)}}})])]),e("LoadingSpinner",{attrs:{loading:t.isLoading}}),e("button",{staticClass:"outlined-btn",on:{click:t.updateColumnWriter}},[t._v(" 수정하기 ")])],1)])])},r=[],i=(e("b0c0"),e("96cf"),e("1da1")),o=e("7632"),c=e("4bae"),u={components:{LoadingSpinner:c["a"]},data:function(){return{name:"",job:"",contents:"",errMsg:"",id:"",isLoading:!1}},created:function(){this.id=this.$route.params.id,this.fetchColumnWriterDetail(this.id)},methods:{goto:function(t){this.$router.push({path:t})},fetchColumnWriterDetail:function(t){var n=this;return Object(i["a"])(regeneratorRuntime.mark((function e(){var a,r;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return n.isLoading=!0,e.prev=1,e.next=4,Object(o["j"])(t);case 4:a=e.sent,r=a.data,n.name=r.name,n.job=r.job,n.contents=r.contents,e.next=14;break;case 11:e.prev=11,e.t0=e["catch"](1),console.log(e.t0);case 14:return e.prev=14,n.isLoading=!1,e.finish(14);case 17:case"end":return e.stop()}}),e,null,[[1,11,14,17]])})))()},updateColumnWriter:function(){var t=this;return Object(i["a"])(regeneratorRuntime.mark((function n(){var e,a;return regeneratorRuntime.wrap((function(n){while(1)switch(n.prev=n.next){case 0:if(t.name){n.next=4;break}return t.errMsg="이름을 입력해주세요",alert(t.errMsg),n.abrupt("return",!1);case 4:if(t.job){n.next=8;break}return t.errMsg="직업을 입력해주세요",alert(t.errMsg),n.abrupt("return",!1);case 8:if(t.contents){n.next=12;break}return t.errMsg="내용을 입력해주세요",alert(t.errMsg),n.abrupt("return",!1);case 12:return e={name:t.name,contents:t.contents,job:t.job},t.isLoading=!0,n.prev=14,n.next=17,Object(o["l"])(t.id,e);case 17:a=n.sent,200==a.status?alert("작성자가 수정되었습니다"):alert("에러가 발생했습니다"),n.next=24;break;case 21:n.prev=21,n.t0=n["catch"](14),alert("에러가 발생했습니다");case 24:return n.prev=24,t.isLoading=!1,n.finish(24);case 27:case"end":return n.stop()}}),n,null,[[14,21,24,27]])})))()}}},s=u,l=e("2877"),d=Object(l["a"])(s,a,r,!1,null,null,null);n["default"]=d.exports},"4bae":function(t,n,e){"use strict";var a=function(){var t=this,n=t.$createElement,e=t._self._c||n;return e("div",[t.loading?e("div",{staticClass:"spinner-background"}):t._e(),t.loading?e("div",{staticClass:"spinner"}):t._e()])},r=[],i={components:{},props:{loading:{type:Boolean,required:!0}},data:function(){return{}}},o=i,c=e("2877"),u=Object(c["a"])(o,a,r,!1,null,"6dbc9d3b",null);n["a"]=u.exports},7632:function(t,n,e){"use strict";e.d(n,"g",(function(){return r})),e.d(n,"e",(function(){return i})),e.d(n,"f",(function(){return o})),e.d(n,"h",(function(){return l})),e.d(n,"i",(function(){return d})),e.d(n,"a",(function(){return c})),e.d(n,"k",(function(){return u})),e.d(n,"c",(function(){return s})),e.d(n,"b",(function(){return m})),e.d(n,"l",(function(){return f})),e.d(n,"d",(function(){return b})),e.d(n,"j",(function(){return p}));var a=e("365c");function r(){return a["a"].get("api/column/totalCount")}function i(t){return a["a"].get("api/column/all/".concat(t))}function o(t){return a["a"].get("api/column/detail/".concat(t))}function c(t){return a["b"].post("api/admin/column/new",t)}function u(t,n){return a["b"].put("api/admin/column/update/".concat(t),n)}function s(t){return a["b"].delete("api/admin/column/delete",{data:t})}function l(t){return a["b"].get("api/columnWriter/all/".concat(t))}function d(){return a["b"].get("api/columnWriter/all")}function p(t){return a["b"].get("api/columnWriter/detail/".concat(t))}function m(t){return console.log(t),a["b"].post("api/admin/columnWriter/new",t)}function f(t,n){return a["b"].put("api/admin/columnWriter/update/".concat(t),n)}function b(t){return a["b"].delete("api/admin/columnWriter/delete",{data:t})}}}]);
//# sourceMappingURL=chunk-f3498b32.746397f4.js.map