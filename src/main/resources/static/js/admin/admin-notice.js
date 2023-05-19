
/* 목록 불러오기 */

const $NoticeListWrap = $('.notice-list-wrap');
function showNoticeList(noticeLists) {
    let text = "";
    noticeLists.forEach((noticeList, i) => {
        const formattedDate = formatDate(new Date(noticeList.createdDate));
        text += `
            <tr class="list-content">
                <td>
                    <label class="check-label">
                        <input type="checkbox" name="check">
                    </label>
                </td>
                <td class="show-modal notice-id">${noticeList.id}</td>
                <td class="show-modal">${noticeList.noticeTitle}</td>
                <td class="show-modal">${noticeList.noticeContent}</td>
                <td class="show-modal">관리자</td>
                <td class="show-modal">${formattedDate}</td>
            </tr>
        `;
    })
    $NoticeListWrap.append(text);
}

/* ============================ 페이지 설정 ==================================== */
globalThis.page = 1;

/* 한 페이지에 보여질 페이지의 개수 */
const PAGE_AMOUNT = 10;

/* 데이터 요청할 페이지 */
function setPage(page) {
    globalThis.page = page;
    adminNoticeService.getNoticeList();
}


/* 페이지 버튼 만들기 */
const $contentWrap = $('.paging-wrap');

function pagination(data) {
    let pageable = data.pageable;

    /* 현재 페이지 */
    let pageNumber = pageable.pageNumber;

    let count = Math.floor(pageNumber / PAGE_AMOUNT);
    /* 시작 페이지 */
    let startPage = count * PAGE_AMOUNT;
    /* 끝 페이지 */
    let endPage = startPage + PAGE_AMOUNT;

    endPage = endPage > data.totalPages ? data.totalPages : endPage;

    let hasPrev = startPage > 1;
    let hasNext = endPage < data.totalPages;

    /* 페이지 버튼 추가하는 HTML 코드 작성부 */
    let text =  "";

    if (pageable == null) {
        text = ``;
    } else {
        text = `
                <!-- 페이지 버튼 -->
                <div class="page-button-box-layout">
                    <div class="page-button-box">
                        <!-- 페이지 번호 -->
                `
        if(hasPrev) {
            text += `
                        <!-- 페이지 개수 10개 이상 -->
                        <div class="">
                            <div class="page-button-margin">
                                <div>
                                    <a href="javascript:setPage(${startPage})" ><img src="https://cdn3.iconfinder.com/data/icons/google-material-design-icons/48/ic_keyboard_arrow_left_48px-128.png" class="left-button"></a>
                                </div>
                            </div>
                        </div>
                    `
        }
        for(let i = startPage + 1; i < endPage + 1; i++) {
            let page = i
            /* 현재 페이지가 내가 선택한 페이지 일 경우 */
            if (pageNumber + 1 == page) {
                text += `<div onclick="setPage(${i})" class="page-button page-button-active"> `
            } else {
                text += ` <div onclick="setPage(${i})" class="page-button"> `
            }
            text += `
                    <div class="page-button-margin">
                            <div>
                                <span>${i}</span>
                            </div>
                        </div>
                    </div>
                `
        }

        if (hasNext) {
            text += `
                        <div class="">
                            <div class="page-button-margin">
                                <div>
                                    <a href="javascript:setPage(${endPage + 1})"><img src="https://cdn3.iconfinder.com/data/icons/google-material-design-icons/48/ic_keyboard_arrow_right_48px-128.png" class="right-button"></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 페이지 버튼 끝 -->
            `
        }
    }
    $contentWrap.append(text);
}


/* ajax 모듈 */

let adminNoticeService = (function() {

    /* 목록 불러오기 */
    function getNoticeList() {
        $.ajax({
            url: `/admin/admin-noticeList/${globalThis.page}`,
            success: function(data) {
                $NoticeListWrap.empty();
                showNoticeList(data.content);
                $contentWrap.empty();
                pagination(data)
            }
        })
    }

    /* 상세보기 */
    function noticeDetail(id) {
        $.ajax({
            url: "/admin/notice-detail",
            data: {"id" : id},
            success: function(noticeDTO) {
                showNoticeDetail(noticeDTO);
            },
            error: function(a, b, c) {
                console.log(a);
                console.log(b);
                console.log(c);
            }
        })
    }

    /* 작성 */
    function noticeWrite() {
        // 공지 파일 DTO 설정
        let noticeFiles = [];

        fileList.forEach((file, i)=> {
            let noticeFileDTO = {
                fileOrgName: file[0].name,
                filePath: path[i][0],
                fileUuid: Uuid[i][0]
            };
            noticeFiles.push(noticeFileDTO);
        })

        // 공지 DTO 설정
        let noticeDTO = new Object();
        noticeDTO.noticeTitle = $('#noticeTitle').val();
        noticeDTO.noticeContent = $('#noticeContent').val();
        noticeDTO.noticeFileDTOS = noticeFiles;

        $.ajax({
            url: "/admin/notice-write",
            type: "post",
            data: JSON.stringify(noticeDTO),
            contentType: "application/json; charset=utf-8",
            success: function () {
                $('.add-modal').fadeOut(500);
                adminNoticeService.getNoticeList();
                $('#noticeTitle').val("")
                $('#noticeContent').val("")
            }
        })
    }

    /* 수정 */

    /* 삭제 */
    function noticeDelete(id) {
        $.ajax({
            url: "/admin/notice-delete",
            type: "delete",
            data: {"id": id},
            success: function() {
                if ($NoticeListWrap.children() == null) {
                    globalThis.page = globalThis.page - 1;
                    adminNoticeService.getNoticeList();
                    return;
                }
                adminNoticeService.getNoticeList();
            }
        })
    }

    return {getNoticeList: getNoticeList, noticeDetail: noticeDetail, noticeWrite: noticeWrite, noticeDelete: noticeDelete}

})();

/* =========================== 상세보기 모달 =============================== */

$("table.table").on('click','.show-modal' , function(e){
    let id = $($(this).parent().children()[1]).text();
    console.log(id)
    $(".modal").show();
    adminNoticeService.noticeDetail(id);
});

/* ===================================== 공지사항 작성 ================================ */

const $writeNoticeButton = $('.regist-button');

$writeNoticeButton.on('click', function () {
    let noticeTitle = $('#noticeTitle').val();
    let noticeContent = $('#noticeContent').val();

    if (noticeTitle != null && noticeContent != null) {
        adminNoticeService.noticeWrite();
    }
})

/* =========================== 공지사항 삭제 =============================== */

const $deleteButton = $('#confirm-delete');
let checkBoxArr = [];

$deleteButton.on('click', function () {
    var $checkBoxs = $('.list-content input[type="checkbox"]');

    $checkBoxs.each((i, v) => {
        if (v.checked) {
            checkBoxArr.push($('.notice-id').eq(i).text())
        }
    });

    for (let i = 0; i < checkBoxArr.length; i++) {
        adminNoticeService.noticeDelete(checkBoxArr[i])
    }
});


/* ========================= 상세 모달 채우기 =================================== */

function showNoticeDetail(noticeDTO) {
    const formattedDate = formatDate(new Date(noticeDTO.updatedDate));
    let text = "";
    const $detailWrap = $('.content-detail');

    text = `
        <div class="content-img-wrapper">
        `
    if (noticeDTO.noticeFileDTOS != null) {
        for(let i = 0; i < noticeDTO.noticeFileDTOS.length; i++) {
            text += `
                <label>
                    <div class="content-img list-img img__width">
                        <img
                            src="/image/display?fileName=${noticeDTO.noticeFileDTOS[i].filePath}/${noticeDTO.noticeFileDTOS[i].fileUuid}_${noticeDTO.noticeFileDTOS[i].fileOrgName}"
                        />
                    </div>
                    <input type="file" name="file" accept="image/*" style="display: none" />
                </label>
            `
        }
    }
    text +=
        `
        </div>
        <ul class="content-list-wrap">
            <li class="content-list">
                <span>제목</span>
                <div class="content-input">
                    <input type="text" value="${noticeDTO.noticeTitle}" />
                </div>
            </li>
            <li class="content-list">
                <span>날짜</span>
                <div class="content-input">
                    <input type="text" value="${formattedDate}" />
                </div>
            </li>
            <li class="content-list txt-align">
                <span>내용</span>
                <div class="content-input">
                    <textarea class="normal-textarea" cols="30" rows="7" name="" >${noticeDTO.noticeContent}</textarea>
                </div>
            </li>
        </ul>
        <div class="button-box">
            <button type="button" class="update-button">수정하기</button>
        </div>
    `;
    $detailWrap.empty();
    $detailWrap.append(text);
}


// 맨 처음 화면에 데이터 뿌려주기
adminNoticeService.getNoticeList();