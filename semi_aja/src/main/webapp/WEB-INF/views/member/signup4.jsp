<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/WEB-INF/views/common/header.jsp"%>

<style>
    .errormessage {
        font-size: 10px;
        text-align: center;
        padding-top: 0;
        margin-top: 0;
        margin-bottom: 50px;
        color: red;
    }
    .inputsize {
        width: 300px;
    }
    .profileInput {
        display: flex;
        flex-direction: row;
        width: 400px;
        margin-bottom: 42px;
    }
    .profilefont {
        font-size: 12px;
        width: 100px;
        height: 22px;
        text-align: center;
        display: flex;
        align-items: center;
    }
    .profileInput>input {
        width: 61%;
    }
    .profileInput>input,
    .profileInput>select {
        border: none;
        border-bottom: 1px solid black;
        outline: none;
        font-size: 12.5px;
        padding-left: 8px;
    }
    select {
        width: 17%;
        border: none;
        border-bottom: 1px solid black;
        outline: none;
        appearance: none; /* 기본 화살표 제거 */
    }
    #editprofile-container {
        width: 400px;
        display: flex;
        flex-direction: column;
        align-items: start;
    }
    #editprofile-text {
        display: flex;
        flex-direction: column;
        align-items: start;
    }
    #editorprofile-text div {
        text-align: left;
    }
    div#editprofile {
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-top: 70px;
        margin-bottom: 150px;
    }
    .custom-radio {
        display: none;
    }
    .custom-label {
        display: inline-block;
        width: 9px;
        height: 9px;
        border: 2px solid black;
        border-radius: 3px;
        position: relative;
        cursor: pointer;
        margin-right: 10px;
    }
    .custom-label::after {
        content: '';
        position: absolute;
        top: 50%;
        left: 50%;
        width: 12px;
        height: 12px;
        background-color: black;
        transform: translate(-50%, -50%) scale(0);
        transition: transform 0.2s ease-in-out;
        border-radius: 2px;
    }
    .custom-radio:checked + .custom-label::after {
        transform: translate(-50%, -50%) scale(0.5);
    }
    .gender-option {
        display: flex;
        align-items: center;
        margin-right: 20px;
    }
    .gender-option input[type="radio"] {
        margin-right: 5px;
    }
    .button-container {
        display: flex;
        justify-content: center;
        width: 100%;
        margin-top: 20px;
    }
    .button-container button {
        margin: 0 10px;
        font-size: 10.5px;
        background-color: black;
        color: rgb(235, 235, 235);
    }
    p {
        font-size: 12.5px;
        position: relative;
        top: 7.0px;
    }
    input[type='button'] {
        font-size: 8px;
        background-color: black;
        color: rgb(235, 235, 235);
    }
</style>

<section>
    <div id="editprofile">
        <div id="editprofile-container">
            <div id="editprofile-text">
                <div style="height: 100px;">
                    <h5>회원가입</h5>
                </div>
                <div style="height: 80px;">
                    <h6>INFORMATION</h6>
                </div>
            </div>
            <form action="<%=request.getContextPath() %>/member/signupend.do" method="POST" id="signupForm">
                <div id="profileId" class="profileInput">
                    <div class="profilefont">아이디</div>
                    <input type="text" name="custEmail">
                </div>
                <div id="emailIdMessage" class="errormessage"></div>
                <div id="new-profilePw" class="profileInput">
                    <div class="profilefont">비밀번호</div>
                    <input type="password" name="custPw" id="Password">
                </div>
                <div id="passwordMessage" class="errormessage"></div>
                <div id="new-profilePw-check" class="profileInput">
                    <div class="profilefont">비밀번호 확인</div>
                    <input type="password" name="custPwCheck" id="PasswordCheck">
                </div>
                <div id="passwordCheckMessage" class="errormessage"></div>
                <div id="profileName" class="profileInput">
                    <div class="profilefont">이름</div>
                    <input type="text" name="custName" id="custName">
                </div>
                <div id="nameMessage" class="errormessage"></div>
                <div id="profileNickname" class="profileInput">
                    <div class="profilefont">닉네임</div>
                    <input type="text" name="custNickname">
                </div>
                <div id="nicknameMessage" class="errormessage"></div>
                <div id="profilePhone" class="profileInput">
                    <div class="profilefont">전화번호</div>
                    <select name="phone-first" id="phone-first">
                        <option value="010">010</option>
                        <option value="011">011</option>
                        <option value="016">016</option>
                        <option value="017">017</option>
                        <option value="018">018</option>
                        <option value="019">019</option>
                    </select>
                    <input type="text" name="phone-second" style="width: 20%; margin-left: 5px; margin-right: 5px;">
                    <input type="text" name="phone-last" style="width: 20%;">
                </div>
                <div id="phoneMessage" class="errormessage"></div>
                <div id="profileBirth" class="profileInput">
                    <div class="profilefont">생년월일</div>
                    <input type="date" name="custBirth">
                </div>
                <div id="birthMessage" class="errormessage"></div>
                <div id="profileGender" class="profileInput">
                    <div class="profilefont">성별</div>
                    <div class="gender-option">
                        <input type="radio" id="female" name="custGender" value="F" class="custom-radio">
                        <label for="female" class="custom-label"></label>
                        <p>Female</p>
                    </div>
                    <div class="gender-option">
                        <input type="radio" id="male" name="custGender" value="M" class="custom-radio">
                        <label for="male" class="custom-label"></label>
                        <p>Male</p>
                    </div>
                </div>
                <div id="genderMessage" class="errormessage"></div>
                <div id="profileAddress" class="profileInput">
                    <div class="profilefont">주소</div>
                    <div class="address-content inputsize">
                        <div class="profileInput">
                            <input type="text" id="sample6_postcode" name="custPostcode" placeholder="우편번호" style="width: 45%;">
                            <input type="button" onclick="sample6_execDaumPostcode()" value="주소찾기" style="width: 15%;">
                        </div>
                        <div class="profileInput">
                            <input type="text" id="sample6_address" name="custAddress" placeholder="주소">
                        </div>
                        <div class="profileInput">
                            <input type="text" id="sample6_detailAddress" name="custDetailAddress" placeholder="상세주소">
                        </div>
                        <div>
                            <input type="text" id="sample6_extraAddress" placeholder="참고항목">
                        </div>
                    </div>
                </div>
                <div id="addressMessage" class="errormessage"></div>
                <div class="button-container">
                    <button type="button" onclick="signUp()">회원가입</button>
                </div>
            </form>
        </div>
    </div>
</section>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                if (data.userSelectedType === 'R') {
                    addr = data.roadAddress;
                } else {
                    addr = data.jibunAddress;
                }

                if (data.userSelectedType === 'R') {
                    if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                        extraAddr += data.bname;
                    }
                    if (data.buildingName !== '' && data.apartment === 'Y') {
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    if (extraAddr !== '') {
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }

    // 유효성 검사

    // 아이디 중복 체크 함수 / 이메일 유효성 검사(@와 .이 있어야 함)

    const emailInput = document.querySelector("input[name='custEmail']");
    const emailMessage = document.getElementById("emailIdMessage");
    
    const checkIdDuplicate = () => {
        const custEmail = emailInput.value.trim();
        if (custEmail === "") {
        	emailMessage.innerText = "이메일을 입력해주세요.";
            return false;
        }

        const emailRegExp = /^[a-z0-9]+@[a-z0-9]+\.[a-z]{2,6}$/;
        if (!emailRegExp.test(custEmail)) {
            emailMessage.innerText = "이메일주소가 유효하지 않습니다.";
            return false;
        }

        fetch('<%=request.getContextPath() %>/member/checkIdDuplicate.do', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ custEmail: custEmail })
        })
        .then(response => response.json())
        .then(data => {
            if (data.isDuplicate) {
            	emailMessage.innerText = "이미 사용 중인 이메일입니다.";
            } else {
            	emailMessage.innerText = "";
            }
        })
        return true;
    	};

    emailInput.addEventListener('blur', checkIdDuplicate);


    // 이름 유효성 검사 (한글만 허용)
    const nameInput = document.querySelector("input[name='custName']");
    const nameMessage = document.getElementById('nameMessage');

    const nameInputChangeHandler = () => {
        const custName = nameInput.value.trim();
        if (custName === "") {
            nameMessage.innerText = "이름을 입력해주세요.";
            return false;
        }
        const nameRegExp = /^[가-힣]+$/;
        if (!nameRegExp.test(custName)) {
            nameMessage.innerText = "이름은 한글만 입력 가능합니다.";
            return false;
        } else {
            nameMessage.innerText = "";
            return true;
        }
    }

    nameInput.addEventListener('input', nameInputChangeHandler);

    // 비밀번호 유효성 검사 (8글자 이상, 영문, 숫자, 특수문자 포함)
    const passwordInput = document.querySelector("input[name='custPw']");
    const passwordMessage = document.getElementById('passwordMessage');

    const passwordInputChangeHandler = () => {
        const password = passwordInput.value.trim();
        if (password === "") {
            passwordMessage.innerText = "비밀번호를 입력해주세요.";
            return false;
        }
        const passwordRegExp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
        if (!passwordRegExp.test(password)) {
            passwordMessage.innerText = "비밀번호는 8글자 이상, 영문, 숫자, 특수문자(@$!%*#?&)를 모두 포함해야 합니다.";
            return false;
        } else {
            passwordMessage.innerText = "";
            return true;
        }
    }

    passwordInput.addEventListener('input', passwordInputChangeHandler);

    // 비밀번호 확인 유효성 검사
    const passwordCheckInput = document.querySelector("input[name='custPwCheck']");
    const passwordCheckMessage = document.getElementById('passwordCheckMessage');

    const newPasswordCheckInputChangeHandler = () => {
        const password = passwordInput.value.trim();
        const passwordCheck = passwordCheckInput.value.trim();
        if (passwordCheck === "") {
            passwordCheckMessage.innerText = "비밀번호 확인을 입력해주세요.";
            return false;
        }
        if (password !== passwordCheck) {
            passwordCheckMessage.innerText = "비밀번호와 비밀번호 확인이 일치하지 않습니다.";
            return false;
        } else {
            passwordCheckMessage.innerText = "";
            return true;
        }
    }

    passwordCheckInput.addEventListener('input', newPasswordCheckInputChangeHandler);

    // 전화번호 유효성 검사 (숫자만 허용, 4자리씩만 입력)
    const phoneSecondInput = document.querySelector("input[name='phone-second']");
    const phoneLastInput = document.querySelector("input[name='phone-last']");
    const phoneMessage = document.getElementById('phoneMessage');

    const phoneInputChangeHandler = () => {
        const phoneSecond = phoneSecondInput.value.trim();
        const phoneLast = phoneLastInput.value.trim();
        if (phoneSecond === "" || phoneLast === "") {
            phoneMessage.innerText = "전화번호를 입력해주세요.";
            return false;
        }
        const phoneRegExp = /^\d{4}$/;
        if (!phoneRegExp.test(phoneSecond) || !phoneRegExp.test(phoneLast)) {
            phoneMessage.innerText = "전화번호는 숫자만 입력 가능하며, 각각 4자리씩 입력되어야 합니다.";
            return false;
        } else {
            phoneMessage.innerText = "";
            return true;
        }
    }

    phoneSecondInput.addEventListener('input', phoneInputChangeHandler);
    phoneLastInput.addEventListener('input', phoneInputChangeHandler);

    // 생년월일 유효성 검사
    const birthInput = document.querySelector("input[name='custBirth']");
    const birthMessage = document.getElementById('birthMessage');

    const birthInputChangeHandler = () => {
        const custBirth = birthInput.value.trim();
        if (custBirth === "") {
            birthMessage.innerText = "생년월일을 입력해주세요.";
            return false;
        } else {
            birthMessage.innerText = "";
            return true;
        }
    }

    birthInput.addEventListener('input', birthInputChangeHandler);

    // 성별 유효성 검사
    const genderInputs = document.querySelectorAll("input[name='custGender']");
    const genderMessage = document.getElementById('genderMessage');

    const genderInputChangeHandler = () => {
        let genderSelected = false;
        genderInputs.forEach(input => {
            if (input.checked) {
                genderSelected = true;
            }
        });
        if (!genderSelected) {
            genderMessage.innerText = "성별을 선택해주세요.";
            return false;
        } else {
            genderMessage.innerText = "";
            return true;
        }
    }

    genderInputs.forEach(input => input.addEventListener('change', genderInputChangeHandler));

    // 주소 유효성 검사
    const postcodeInput = document.querySelector("input[name='custPostcode']");
    const addressInput = document.querySelector("input[name='custAddress']");
    const detailAddressInput = document.querySelector("input[name='custDetailAddress']");
    const addressMessage = document.getElementById('addressMessage');

    const addressInputChangeHandler = () => {
        const postcode = postcodeInput.value.trim();
        const address = addressInput.value.trim();
        const detailAddress = detailAddressInput.value.trim();
        if (postcode === "" || address === "" || detailAddress === "") {
            addressMessage.innerText = "주소를 모두 입력해주세요.";
            return false;
        } else {
            addressMessage.innerText = "";
            return true;
        }
    }

    postcodeInput.addEventListener('input', addressInputChangeHandler);
    addressInput.addEventListener('input', addressInputChangeHandler);
    detailAddressInput.addEventListener('input', addressInputChangeHandler);

    function signUp() {
        const isEmailValid = checkIdDuplicate();
        const isNameValid = nameInputChangeHandler();
        const isPasswordValid = passwordInputChangeHandler();
        const isPasswordCheckValid = newPasswordCheckInputChangeHandler();
        const isPhoneValid = phoneInputChangeHandler();
        const isBirthValid = birthInputChangeHandler();
        const isGenderValid = genderInputChangeHandler();
        const isAddressValid = addressInputChangeHandler();

        if (isEmailValid && isNameValid && isPasswordValid && isPasswordCheckValid && isPhoneValid && isBirthValid && isGenderValid && isAddressValid) {
            document.getElementById("signupForm").submit();
        } else {
            alert("입력한 정보를 다시 확인해주세요.");
        }
    }
</script>

<%@ include file = "/WEB-INF/views/common/footer.jsp"%>
