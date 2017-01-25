    var treeData = {};  
      
    function getTree(obj, tree) {  
        treeData = tree;  
        var data = tree.data;  
        var treeContentStart = "";  
        var treeContentEnd = "";  
      
        if(data.length > 0) {  
            treeContentStart += '<ul class="nav tree-ul-first" role="tablist">';  
            $.each(data, function(i, n) {  
                var menuLeftIcon = n.menuLeftIcon ? n.menuLeftIcon : 'glyphicon glyphicon-paperclip';  
                if(n.menuChildData && n.menuChildData.length > 0) {  
                    treeContentStart += '<li role="presentation">' +  
                        '<a href="javascript:void(0);" data-url="' + n.menuUrl + '" onclick="openChildNotes(this)">' +  
                        '<span class="tree-span-left ' + menuLeftIcon + '"></span>' + n.menuName + '<span class="' + tree.clickIconBefore + ' tree-span-right"></span></a>';  
                    treeContentStart += fillTreeData(n.menuChildData);  
                } else {  
                    treeContentStart += '<li role="presentation">' +  
                        '<a href="' + n.menuUrl + '">' +  
                        '<span class="tree-span-left ' + menuLeftIcon + '"></span>' + n.menuName + '</a>';  
                }  
                treeContentStart += '</li>';  
            });  
            treeContentEnd += "</ul>";  
        }  
        obj.html(treeContentStart + treeContentEnd);  
    }  
      
    function fillTreeData(data) {  
        var treeContentStart = "";  
        var treeContentEnd = "";  
        if(data.length > 0) {  
            treeContentStart += '<ul class="nav tree-ul-childs">';  
            $.each(data, function(i, n) {  
                var menuLeftIcon = n.menuLeftIcon ? n.menuLeftIcon : 'glyphicon glyphicon-paperclip';  
                if(n.menuChildData && n.menuChildData.length > 0) {  
                    treeContentStart += '<li>' +  
                        '<a href="javascript:void(0);" data-url="' + n.menuUrl + '" onclick="openChildNotes(this)"><span class="tree-span-left ' + menuLeftIcon + '"></span>' + n.menuName + '<span class="' + treeData.clickIconBefore + ' tree-span-right"></span></a>';  
                    treeContentStart += fillTreeData(n.menuChildData);  
                } else {  
                    treeContentStart += '<li>' +  
                        '<a href="' + n.menuUrl + '"><span class="tree-span-left ' + menuLeftIcon + '"></span>' + n.menuName + '</a>';  
                }  
                treeContentStart += '</li>';  
            });  
            treeContentEnd += "</ul>";  
        }  
        return treeContentStart + treeContentEnd;  
    }  
      
    function openChildNotes(obj) {  
        var o = $(obj);  
        var showState = o.next().css("display");  
        var leftOff = o.css("padding-left");  
        var paddingLeft = leftOff.substring(0, leftOff.length - 2);  
        if(showState == "block") {  
            showState = "none";  
            o.find("span").last().removeClass(treeData.clickIconAfter).addClass(treeData.clickIconBefore);  
            o.next().find("li").find("a").css("padding-left", (parseInt(paddingLeft) - 20) + 'px');  
            $("ul", o.next("ul")).css("display", showState);  
            $("li a span.tree-span-right", o.next("ul")).removeClass(treeData.clickIconAfter).addClass(treeData.clickIconBefore);  
        } else {  
            showState = "block";  
            o.find("span").last().removeClass(treeData.clickIconBefore).addClass(treeData.clickIconAfter);  
            o.next().find("li").find("a").css("padding-left", (parseInt(paddingLeft) + 20) + 'px');  
        }  
        o.next("ul").css("display", showState);  
    }  