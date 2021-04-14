function ChangeSearchText() {

	var value = $("#menu_search_text").val();
	var table = $('#dataTable').DataTable();

	table.search(value).draw();

	}

	$(document).ready(function () {
	$("#menu_search_text").keypress(function (e) {
	if (e.which == 13) {
		location.href = "index.jsp?langType=" + $("#langType").val() + "#searchProduct";
	}
	});
	});

	$(document).ready(function () {
		// 연혁 테이블 바디를 만든다.
		$("#historyModal").on("show.bs.modal", function (event) {

			var langType = $("#langType").val();


			$.ajax({
				type: 'POST',
				url: 'getHistoryCompany',
				data: {
					'langType': langType
				}

			}).done(function (data) {

				$('#historyTableBody').empty();

				let historyItem = '';

				var historyKey = 0;

				$.each(data, function (key, value) {
					historyItem += '<tr>';
					historyItem += '<td id="td-history-dt">' + value.historyDt + '</td>';
					historyItem += '<td>' + value.historySubject + '</td>';
					historyItem += '</tr>';

					// 5로우마다 빈줄
					if ((++historyKey % 5) == 0) {
						historyItem += '<tr><td>　</td><td> </td> </tr>';
					}
				});

				$('#historyTableBody').append(historyItem);

			});

		});
	});

	$(document).ready(function () {
		// 비전 테이블 바디를 만든다.
		$("#visionModal").on("show.bs.modal", function (event) {

			var langType = $("#langType").val();

			$.ajax({
				type: 'POST',
				url: 'getvisionCompany',
				data: {
					'langType': langType
				}

			}).done(function (data) {

				$('#vision-body-content').empty();

				$.each(data, function (key, value) {
					$('#vision-body-content').append(value.content);
				});

			});

		});
	});

	$(document).on("click", "ul.product-filters li", function (e) {

		// 기존에 해당 클래스가 있으면 제거한다.
		// 원인파악.
		// if ( $('.product-container') > 0 ) { 
			$('.product-container').isotope('destroy');
		// }

		$('#product-filter-items').show();

		$("#product-filters li").removeClass('filter-active');
		$(this).addClass('filter-active');

		$('.product-container').isotope({
			itemSelector: '.product-item',
			layoutMode: 'fitRows',
			filter: $(this).data('filter') // 초기값 아무것도 없이 올리기
		});

		// productIsotope.isotope({ filter: $(this).data('filter') });



	});

	$(document).on("click", "div.product-info a", function (e) {
		// 선택한 품목의 스펙을 볼 수 있는 링크를 만들어 준다.
		var langType = $("#langType").val();
		var itmId = e.currentTarget.getAttribute("id");

		$.ajax({
			type: 'POST',
			url: 'getSpecification',
			data: {
				'langType': langType,
				'itmId': itmId
			}

		}).done(function (data) {

			// 스펙이 출력되는 테이블 바디를 삭제하고.. 아래 로직에서 새로 추가한다.
			$('#specificationTableBody').empty();

			let specItem = '';
			let specItemKey = 1;
			$.each(data, function (key, value) {

				// 모달창의 제목을 입력한다.
				if (key == 1) {
					$('#specModalTitle').text(value.itmNm);
				}


				specItem += '<tr>';
				specItem += '<th scope="row">' + (specItemKey++) + '</th>';
				specItem += '<td>' + value.specNm + '</td>';
				specItem += '<td>' + value.specValue + '</td>';
				specItem += '</tr>';
			});

			$('#specificationTableBody').append(specItem);

		});
	});


	$(function () {
		$('#selectCateNo').on("change", function () {

			// 기존에 추가된 자식 노드를 모두 삭제한다.
			var filter = document.getElementById("product-filters");
			while (filter.hasChildNodes()) {
				filter.removeChild(filter.firstChild);
			}

			var filterItems = document.getElementById("product-filter-items");
			while (filterItems.hasChildNodes()) {
				filterItems.removeChild(filterItems.firstChild);
			}

			// 아래 함수를 돌면서 자식노드를 추가한다.
			createFilter();

			createFilterItems();


			// 초기 조회 시 전체 출력이 안되고 필터 선택이 안된 상태로 필터목록만 보여주도록 한다.
			$('#product-filter-items').hide();


		});
	});

	function createFilter() {
		var cateNo = $('#selectCateNo option:selected').val();
		var langType = $("#langType").val();

		$.ajax({
			type: 'POST',
			url: 'getFieldList',
			data: {
				'langType': langType,
				'cateNo': cateNo
			}

		}).done(function (data) {

			$('#product-filters').empty();

			let filter = '';

			$.each(data, function (key, value) {

				filter += '<li data-filter=".filter-' + value.fieldNo + '" > ' + value.fieldNm + '(' + value.itmCount + ')</li>';

			});
			$('#product-filters').append(filter);
		});
	}

	function createFilterItems() {
		var cateNo = $('#selectCateNo option:selected').val();
		var langType = $("#langType").val();


		$.ajax({
			type: 'POST',
			url: 'getFieldItemList',
			data: {
				'langType': langType,
				'cateNo': cateNo
			}

		}).done(function (data) {

			$('#product-filter-items').empty();


			let filteritems = '';

			$.each(data, function (key, value) {

				filteritems += '<div class="col-lg-4 col-md-6 product-item filter-' + value.fieldNo + '" data-wow-delay="10.1s">';
				filteritems += '<div class="product-wrap">';

				// #region figure
				filteritems += '<figure>';

				if (!value.tp100900Id) {
					// filteritems += '<img src="/img/product/no_image.png" class="img-fluid" alt="" >';
				} else {
					filteritems += '<img src="/link/sdc112/' + value.tp100900Id + '" class="img-fluid" alt="">';
				}


				if (!value.tP100901Id) {
					// filteritems += '<a href="/img/product/no_image.png" data-lightbox="product" data-title="App 1" class="link-preview" title="Preview"><i class="ion ion-eye"></i></a>';
				} else {
					filteritems += '<a href="/link/sdc112/' + value.tP100901Id + '" data-lightbox="product" data-title="' + value.fieldNm + '>' + value.itmNm + '" class="link-preview" title="Preview"><i class="ion ion-eye"></i></a>';
				}

				if (!value.tP100100Id) {
					// filteritems += '<a href="link/sdc112/abc.pdf" class="link-details" title="More Details"><i class="ion ion-android-open"></i></a>';
				} else {
					filteritems += '<a href="/link/sdc112/' + value.tP100100Id + '" download = "' + value.tP100100Nm + '" class="link-details" title="DownLoad File"><i class="ion ion-android-open"></i></a>';
				}

				filteritems += '</figure>';
				// #endregion figure

				filteritems += '<div class="product-info">';
				filteritems += '<h4><font color=black>' + value.itmNm + '</font></h4>';
				filteritems += '<p>' + value.fieldNm + '</p>';


				filteritems += '<p><a href="#specModal" id="' + value.itmId + '" class="link-preview" title="Specification" data-toggle="modal" data-target="#specModal"><i class="ion ion-eye"></i></a></p>';


				if (!value.tP100100Id) {
					// filteritems += '<a href="link/sdc112/abc.pdf" class="link-details" title="More Details"><i class="ion ion-android-open"></i></a>';
				} else {
					filteritems += '<a href="/link/sdc112/' + value.tP100100Id + '" download = "' + value.tP100100Nm + '" class="link-details" title="DownLoad File"><i class="ion ion-android-open"></i></a>';
				}

				filteritems += '</div>';
				filteritems += '</div>';
				filteritems += '</div>';

			});
			$('#product-filter-items').append(filteritems);
		});

	}

	function goPdfUrl(pdfUrl) {
		// 사용 안함.
	}

