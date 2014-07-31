"use strict";

angular.module('fileUpload', [ 'angularFileUpload' ]);

var uploadUrl = 'http://localhost:8080/nlp-ui/upload';
window.uploadUrl = window.uploadUrl || 'upload';

var MyCtrl = [
		'$scope',
		'$http',
		'$timeout',
		'$upload',
		function($scope, $http, $timeout, $upload) {

			$scope.usingFlash = FileAPI && FileAPI.upload != null;
			$scope.fileReaderSupported = window.FileReader != null
					&& (window.FileAPI == null || FileAPI.html5 != false);
			$scope.uploadRightAway = true;
			$scope.changeAngularVersion = function() {
				window.location.hash = $scope.angularVersion;
				window.location.reload(true);
			};
			$scope.hasUploader = function(index) {
				return $scope.upload[index] != null;
			};
			$scope.abort = function(index) {
				$scope.upload[index].abort();
				$scope.upload[index] = null;
			};
			$scope.angularVersion = window.location.hash.length > 1 ? (window.location.hash
					.indexOf('/') === 1 ? window.location.hash.substring(2)
					: window.location.hash.substring(1))
					: '1.2.20';
			$scope.onFileSelect = function($files) {
				$scope.selectedFiles = [];
				$scope.progress = [];
				if ($scope.upload && $scope.upload.length > 0) {
					for (var i = 0; i < $scope.upload.length; i++) {
						if ($scope.upload[i] != null) {
							$scope.upload[i].abort();
						}
					}
				}
				$scope.upload = [];
				$scope.uploadResult = [];
				$scope.selectedFiles = $files;
				$scope.dataUrls = [];
				for (var i = 0; i < $files.length; i++) {
					var $file = $files[i];
					if ($scope.fileReaderSupported
							&& $file.type.indexOf('image') > -1) {
						var fileReader = new FileReader();
						fileReader.readAsDataURL($files[i]);
						var loadFile = function(fileReader, index) {
							fileReader.onload = function(e) {
								$timeout(function() {
									$scope.dataUrls[index] = e.target.result;
								});
							}
						}(fileReader, i);
					}
					$scope.progress[i] = -1;
					if ($scope.uploadRightAway) {
						$scope.start(i);
					}
				}
			};

			$scope.start = function(index) {
				$scope.progress[index] = 0;
				$scope.errorMsg = null;

				$scope.upload[index] = $upload.upload({
					url : uploadUrl,
					method : 'POST',
					headers : {
						'my-header' : 'my-header-value'
					},
					data : {
						myModel : $scope.myModel
					},
					file : $scope.selectedFiles[index],
					fileFormDataName : 'myFile'
				});
				$scope.upload[index].then(function(response) {
					$timeout(function() {
						$scope.uploadResult.push(response.data);
					});
				}, function(response) {

					if (response.status > 0) {
						$scope.errorMsg = response.status + ': '
								+ response.data;
					}
				}, function(evt) {
					// Math.min is to fix IE which reports 200% sometimes
					$scope.progress[index] = Math.min(100, parseInt(100.0
							* evt.loaded / evt.total));
				});
				$scope.upload[index].xhr(function(xhr) {
					// xhr.upload.addEventListener('abort', function()
					// {console.log('abort complete')}, false);
				});

			};

			$scope.dragOverClass = function($event) {
				var items = $event.dataTransfer.items;
				var hasFile = false;
				if (items != null) {
					for (var i = 0; i < items.length; i++) {
						if (items[i].kind == 'file') {
							hasFile = true;
							break;
						}
					}
				} else {
					hasFile = true;
				}
				return hasFile ? "dragover" : "dragover-err";
			};
		} ];
