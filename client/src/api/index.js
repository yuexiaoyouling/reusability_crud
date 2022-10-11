import request from './request.js'


export  function searchAllStudent(page,rows) { 
    return request({
        url: "/student/"+page+"/"+rows,
        method: 'GET',
    })
}

export  function searchAllClass() {
 
    return request({
        url: "/class",
        method: 'GET',
    })
}

export  function searchStudentByAny(page,rows,sId,sName,cName) {
    return request({
        url: "/student/"+page+"/"+rows+"/"+sId+"/"+sName+"/"+cName,
        method: 'GET',
    })
}
export  function removeStudent(sId) {
    return request({
        url: "/student/"+sId,
        method: 'Delete',
    })
}

export  function updateStudent(sId,sName,Acj,Bcj,Ccj,logo) {
    return request({
        url: "/student",
        params:{
            sId:sId,sName:sName,Acj:Acj,Bcj:Bcj,Ccj:Ccj,logo:logo
        },
        method: 'PUT',
    })
}
