const $NoticeListWrap = $(".info-table table");
let page = 1;
const PAGE_AMOUNT = 10;
const $deleteBtn = $(".delete-button");

function doSearch(pageBtn) {

    if (pageBtn) {
        page = $(pageBtn).data("page");
    }

    $.ajax({
        url: `/admin/inquiry/list`,
        data: {page: page},
        success: function (data) {
            $NoticeListWrap.empty();
            console.log(data);
            appendTableHead();
            data.content.forEach(inquiry => appendList(inquiry));

            showPage(data);
        }
    });
}

function showPage(result) {
    let pageable = result.pageable;
    let pageNumber = pageable.pageNumber;
    let count = Math.floor(pageNumber / PAGE_AMOUNT);
    let startPage = count * PAGE_AMOUNT;
    let endPage = startPage + PAGE_AMOUNT;

    endPage = endPage > result.totalPages ? result.totalPages : endPage;

    let hasPrev = startPage > 1;
    let hasNext = endPage < result.totalPages;

    // 페이징 HTML 태그를 추가하는 코드 작성
    let paging = "";
    paging += `<div class="paging" style="text-align: center">`;

    if (hasPrev) {
        paging += `<a class="page-button" data-page="${startPage}" 
                        onclick="doSearch(this)"><span><</span></a>`;
    }

    for (let i = startPage + 1; i < endPage + 1; i++) {
        let page = i;
        if (pageNumber + 1 != page) {
            paging += `<a class="page-button" data-page="${page}" onclick="doSearch(this)"><span>${page}</span></a>`;
        } else {
            paging += `<span class="page-button-active page-button">${page}</span>`;
        }
    }
    if (hasNext) {
        paging += `<a class="page-button" data-page="${endPage + 1}" onclick="doSearch(this)"><span>></span></a>`
    }

    $(".paging-wrap").html(paging);
}

function appendList(inquiry) {
    let text;

    let isAnswered = inquiry.inquiryStatus !== "STANDBY";

    text = `
        <tr class="table-cel" data-id="${inquiry.id}">
            <td>
                <label class="check-label">
                    <input data-isanswered="${isAnswered}" type="checkbox" name="check">
                </label>
            </td>
            <td>${inquiry.id}</td>
            <td data-isanswered="${isAnswered}" data-id="${inquiry.id}" onclick="ifStandByRedirect(this)">${inquiry.inquiryTitle}</td>
            <td data-isanswered="${isAnswered}" data-id="${inquiry.id}" onclick="ifStandByRedirect(this)">${inquiry.inquiryContent}</td>
            <td data-isanswered="${isAnswered}" data-id="${inquiry.id}" onclick="ifStandByRedirect(this)">${inquiry.userId}</td>
            <td data-isanswered="${isAnswered}" data-id="${inquiry.id}" onclick="ifStandByRedirect(this)">${inquiry.createdDate.toString().split("T")[0]}</td>
            <td>${inquiry.inquiryStatus}</td>
        </tr>
    `;


    $NoticeListWrap.append(text);
}

function appendTableHead() {
    $NoticeListWrap.append(
        `
            <thead>
                <tr>
                    <th>
                        <label class="check-label">
                            <input type="checkbox" id="allSelect" onclick="checkAll(this)">
                        </label>
                    </th>
                    <th>No</th>
                    <th>문의 제목</th>
                    <th>문의 내용</th>
                    <th>작성자</th>
                    <th>작성 날짜</th>
                    <th>진행 상태</th>
                </tr>
            </thead>
        `
    );
}

function ifStandByRedirect(btn) {
    if ($(btn).data("isanswered")) {
        return;
    }
    location.href = `/admin/inquiry/detail/${$(btn).data("id")}`;
}

/* 로딩시 바로 불러옴 */
doSearch();

$deleteBtn.on("click", function () {
    let ids = new Array();
    $("input[name=check]:checked").each((i, e) => {
        // 답변 완료했으면 지나감
        console.log($(e).data("isanswered"));
        if($(e).data("isanswered")) return;
        ids.push($(".table-cel").eq(i).data("id"));
    });

    $.ajax({
        type: "DELETE",
        url: "/admin/inquiry/delete",
        data: JSON.stringify({ids : ids}),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            console.log(response);
            doSearch();
        }
    });
});