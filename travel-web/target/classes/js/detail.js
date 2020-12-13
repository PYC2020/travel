var showL1 = document.querySelector('#qjsm'),
	editable1 = document.querySelector('.editable1');
showL1.onmouseover = function() {
	editable1.style.display = 'block';
}
showL1.onmouseout = function() {
	editable1.style.display = 'none';
}
var showL2 = document.querySelector('#djq'),
	editable2 = document.querySelector('.editable2');
showL2.onmouseover = function() {
	editable2.style.display = 'block';
}
showL2.onmouseout = function() {
	editable2.style.display = 'none';
}
var showL3 = document.querySelector('#zxhb'),
	editable3 = document.querySelector('.editable3');
showL3.onmouseover = function() {
	editable3.style.display = 'block';
}
showL3.onmouseout = function() {
	editable3.style.display = 'none';
}
var showL4 = document.querySelector('#xjhb'),
	editable4 = document.querySelector('.editable4');
showL4.onmouseover = function() {
	editable4.style.display = 'block';
}
showL4.onmouseout = function() {
	editable4.style.display = 'none';
}

function htmlScroll() {
	var top = document.body.scrollTop || document.documentElement.scrollTop;
	if(elFix.data_top < top) {
		elFix.style.position = 'fixed';
		elFix.style.top = 0;
		elFix.style.right = elFix.data_right;
	} else {
		elFix.style.position = 'static';
	}
}

function htmlPosition(obj) {
	var o = obj;
	var t = o.offsetTop;
	var l = o.offsetLeft;
	while(o = o.offsetParent) {
		t += o.offsetTop;
		l += o.offsetLeft;
	}
	obj.data_top = t;
	obj.data_left = l;
}

var oldHtmlWidth = document.documentElement.offsetWidth;
window.onresize = function() {
	var newHtmlWidth = document.documentElement.offsetWidth;
	if(oldHtmlWidth == newHtmlWidth) {
		return;
	}
	oldHtmlWidth = newHtmlWidth;
	elFix.style.position = 'static';
	htmlPosition(elFix);
	htmlScroll();
}
window.onscroll = htmlScroll;

var elFix = document.getElementById('company_static');
htmlPosition(elFix);