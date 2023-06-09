// 임시로 load() 사용
$(".frame-wrap").prepend(
    `
    <div class="frame-left">
        <div class="aside-menu-wrap">
            <nav class="aside-menu">
                <ul class="menu-list">
                    <li><a href="javascript:void(0)" class="menu">공지사항</a></li>
                    <li><a href="javascript:void(0)" class="menu">이용안내</a></li>
                    <li>
                        <a href="javascript:void(0)" class="menu">자주묻는 FAQ</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" class="menu">1:1 문의하기</a>
                    </li>
                </ul>
            </nav>
            <!--// aside-menu -->
            <div class="aside-guide-box">
                <dl>
                    <dt>HappyBox 고객센터</dt>
                    <dd class="phone-num">02-6405-8088</dd>
                    <dd>평일 <span class="text-num-md">09:00 ~ 18:00</span></dd>
                    <dd>주말, 공휴일 휴무</dd>
                </dl>
                <dl>
                    <dt>실시간 채팅 상담</dt>
                    <dd>평일 <span class="text-num-md">09:00 ~ 21:00</span></dd>
                    <dd>토요일 <span class="text-num-md">09:00 ~ 15:00</span></dd>
                    <dd>일요일 <span class="text-num-md">13:00 ~ 21:00</span></dd>
                </dl>
            </div>
            <!--// aside-guide-box -->
        </div>
        <!--// aside-menu-wrap -->
    </div>
    `
);