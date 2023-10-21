getallstudent()
function savestudent(){
    let Id= $('#exampleFormControlInput1').val();
    let name = $('#exampleFormControlInput2').val();
    let address=$('#exampleFormControlInput3').val();
    $.ajax({
        method:"POST",
        contentType:"application/json",
        url:"http://localhost:8080/api/v1/student/savestudent",
        async:true,
        data:JSON.stringify({
            "stuId":Id,
            "stuName":name,
            "stuAddress":address,
        }),
        success:function (data){
            alert("success")
            getallstudent()
        },
        error:function (xhr, exception){
            alert("Error")
        }
    })
}
function updatestudent(){
    let Id= $('#exampleFormControlInput1').val();
    let name= $('#exampleFormControlInput2').val();
    let address=$('#exampleFormControlInput3').val();
    $.ajax({
        method:"PUT",
        contentType:"application/json",
        url:"http://localhost:8080/api/v1/student/updatestudent",
        async:true,
        data:JSON.stringify({
            "stuId":Id,
            "stuName":name,
            "stuAddress":address,
        }),
        success:function (data){
            alert("success")
            getallstudent()
        },
        error(xhr, exception){
            alert("Error")
        }
    })
}
function deletestudent(){
    let Id = $(`#exampleFormControlInput1`).val();
    $.ajax({
        method:"DELETE",
        url:"http://localhost:8080/api/v1/student/deletestudent/"+Id,
        async:true,
        success:function (data){
            alert("success")
            getallstudent()
        },
        error:function (xhr,exception){
            alert("Error")
        }
    })
}
function getallstudent(){
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/v1/student/getallstudent",
        async:true,
        success:function (data){
            if(data.code=="00"){
                $('#studentTable').empty();
                for(let stu of data.content){
                    let id = stu.stuId
                    let name = stu.stuName
                    let address = stu.stuAddress
                    var rows=`<tr><td>${id}</td><td>${name}</td><td>${address}</td></tr>`;
                    $('#studentTable').append(rows);
                }
            }
        },
        error:function (xhr,exception){
            alert("Error")
        }
    })

}
$(document).ready(function (){
    $(document).on('click','#studentTable tr',function (){
        var col0=$(this).find('td:eq(0)').text();
        var col1=$(this).find('td:eq(1)').text();
        var col2=$(this).find('td:eq(2)').text();

        $('#exampleFormControlInput1').val(col0);
        $('#exampleFormControlInput2').val(col1);
        $('#exampleFormControlInput3').val(col2);
    })
})