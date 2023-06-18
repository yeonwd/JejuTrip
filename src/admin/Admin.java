package admin;

import java.util.Scanner;

import main.UI;

public class Admin {

	public static void admin_business_UI() {
	    
	    System.out.println(UI.alignCenter("1. 수정"));
	    System.out.println(UI.alignCenter("2. 삭제"));
	    System.out.println(UI.alignCenter("0. 종료"));
	    
	 }

	public static int admin_business_loop() {
	       
	   boolean loop = true;
	   Scanner scan = new Scanner(System.in);

	   int num = 0;
	   
	   while (loop) {
	      
	   admin_business_UI();
	   
	   System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
	   num = scan.nextInt();
	   
	   if(num ==1) {
	      
	      //1. 수정
	  	 return num;
	      
	   } else if(num ==2) {
	      
	      //2. 삭제
	  	 return num;
	                     
	   } else if(num ==0) {
	      
	      //0. 이전 화면으로 돌아감.
	      
	      System.out.println();
	      System.out.println("❗️ 이전 화면으로 돌아갑니다. ❗️");
	      loop = false;
	      
	   } 
	   
	}
	   return num;
	}



	//-----------------------루프----------------------------------------

	//---------------------------------사업장 관리
		
	public static void manage_business() {
		
		boolean loop = true;
		Scanner scan = new Scanner(System.in);
		
		
		
		while (loop) {
			
			//사업장 관리 UI
			System.out.println(UI.alignCenter("1. 전체 사업장 목록"));
			System.out.println(UI.alignCenter("2. 전체 광고 목록"));
			System.out.println("");
			System.out.println(UI.alignCenter("3. 사업장 찾기"));
			System.out.println(UI.alignCenter("4. 광고 찾기"));
			System.out.println("");
			System.out.println(UI.alignCenter("0. 이전 화면"));
			
			
			//사용자 입력
			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				
				//1. 전체 사업장 목록
				AdminDAO.Business_list();
				
				int num = admin_business_loop();
				if(num==1) {
					AdminDAO.Update_business();
				}
				else if(num==2) {
					AdminDAO.admin_business_delete();
				}
				
						
			} else if(sel.equals("2")) {
				
				//2. 전체 광고 목록
				AdminDAO.Advertisement_list();
				
				int num = admin_business_loop();
				if(num==1) {
					AdminDAO.Update_advertisement();
				}
				else if(num==2) {
					AdminDAO.admin_advertisement_delete();
				}
							
			} else if(sel.equals("3")) {
				
				//3. 사업장 찾기
				System.out.print("🏝️ 검색을 원하시는 사업장 번호를 입력하세요 : ");
				int num = scan.nextInt();
				AdminDAO.Search_business(num);
				UI.delay_loop();
				loop = false;
							
			} else if(sel.equals("4")) {
				
				//4. 광고 찾기
				System.out.print("🏝️ 검색을 원하시는 광고 번호를 입력하세요 : ");
				int num = scan.nextInt();
				AdminDAO.Search_advertisement(num);
				UI.delay_loop();
				loop = false;
				
			} else if(sel.equals("0")) {
				
				//0. 홈화면으로 돌아가기
				System.out.println();
				System.out.println("❗️이전화면❗️");
				loop = false;
				
			}
			
		}
		
	}
	
	public static void manage_trip() {
		
		// 여행 정보 관리
		
		boolean loop = true;
		Scanner scan = new Scanner(System.in);
		
		
		
		while (loop) {
			
			
			System.out.println(UI.alignCenter("1. 관광지 목록"));
			System.out.println(UI.alignCenter("2. 주유소 목록"));
			System.out.println(UI.alignCenter("3. 전기차 충전소 목록"));
			System.out.println(UI.alignCenter("4. 투어 목록"));
			System.out.println("");
			System.out.println(UI.alignCenter("0. 이전 화면"));
			
			
			//사용자 입력
			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				
				//1. 관광지 목록
				AdminDAO.Tour_area_list();
				
				int num = admin_business_loop();
				if(num==1) {
					AdminDAO.Update_tour_area();
				}
				else if(num==2) {
					AdminDAO.admin_Tour_area_delete();
				}
						
			} else if(sel.equals("2")) {
				
				//2. 주유소 목록
				AdminDAO.Oil_bank_list();
				
				int num = admin_business_loop();
				if(num==1) {
					AdminDAO.Update_oil_bank();
				}
				else if(num==2) {
					AdminDAO.admin_Oil_bank_delete();
				}
				
							
			} else if(sel.equals("3")) {
				
				//3. 전기차 충전소 목록
				AdminDAO.Electronic_charge_list();
				
				int num = admin_business_loop();
				if(num==1) {
					AdminDAO.Update_electronic_charge();
				}
				else if(num==2) {
					AdminDAO.admin_Electronic_delete();
				}
							
			} else if(sel.equals("4")) {
				
				//4. 투어 목록
				AdminDAO.Tour_list();
				
				int num = admin_business_loop();
				if(num==1) {
					AdminDAO.Update_tour();
				}
				else if(num==2) {
					AdminDAO.admin_Tour_delete();
				}
				
			} else if(sel.equals("0")) {
				
				//0. 이전화면으로 돌아가기
				System.out.println();
				System.out.println("❗️이전화면❗️");
				loop = false;
				
			}
			
		}
		
	}
	
	public static void manage_reservation() {
		
		// 예약 관리
		
		boolean loop = true;
		Scanner scan = new Scanner(System.in);
		
		
		
		while (loop) {
			
			System.out.println(UI.alignCenter("1. 전체 예약 조회"));
			System.out.println("");
			System.out.println(UI.alignCenter("2. 숙소 예약 관리"));
			System.out.println(UI.alignCenter("3. 액티비티 예약 관리"));
			System.out.println(UI.alignCenter("4. 렌트카 예약 관리"));
			System.out.println("");
			System.out.println(UI.alignCenter("5. 예약 검색"));
			System.out.println(UI.alignCenter("0. 이전 화면"));
			
			
			//사용자 입력
			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				
				//1. 전체 예약 조회
				AdminDAO.Reservation_list();
				
				int num = admin_business_loop();
				if(num==1) {
					AdminDAO.Update_reservation();
				}
				else if(num==2) {
					AdminDAO.admin_reservation_delete();
				}
						
			} else if(sel.equals("2")) {
				
				//2. 숙소 예약 관리
				AdminDAO.Accommodate_reservation_list();
				UI.delay_loop();
							
			} else if(sel.equals("3")) {
				
				//3. 액티비티 예약 관리
				AdminDAO.Activity_reservation_list();
				UI.delay_loop();
							
			} else if(sel.equals("4")) {
				
				//4. 렌트카 예약 관리
				AdminDAO.Rentcar_reservation_list();
				
				int num = admin_business_loop();
				if(num==1) {
					AdminDAO.Update_rentcar_reservation();
				}
				else if(num==2) {
					AdminDAO.admin_Rentcar_Reservation_delete();
				}
				
			} else if(sel.equals("5")) {
				
				//5. 예약 검색
				System.out.print("🏝️ 검색을 원하시는 유저 번호를 입력하세요 : ");
				int num = scan.nextInt();
				AdminDAO.Reservation_list(num);
				UI.delay_loop();
				loop = false;
			}
			else if(sel.equals("0")) {
				
				//0. 이전화면으로 돌아가기
				System.out.println();
				System.out.println("❗️이전화면❗️");
				loop = false;
				
			}
			
		}
		
	}
	
	public static void manage_point() {
		
		//포인트 관리
		
		boolean loop = true;
		Scanner scan = new Scanner(System.in);
		
		
		
		while (loop) {
			
			System.out.println(UI.alignCenter("1. 전체 포인트 충전 내역"));
			System.out.println(UI.alignCenter("2. 포인트 충전 정보 검색"));
			System.out.println("");
			System.out.println(UI.alignCenter("0. 이전 화면"));
			
			
			//사용자 입력
			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				
				//1. 전체 포인트 충전 내역
				AdminDAO.Point_list();
				
				int num = admin_business_loop();
				if(num==1) {
					AdminDAO.Update_user_point();
				}
				else if(num==2) {
					AdminDAO.Update_user_point();
				}
						
			} else if(sel.equals("2")) {
				
				//2. 포인트 충전 정보 검색
				System.out.print("🏝️ 검색을 원하시는 유저 번호를 입력하세요 : ");
				int num = scan.nextInt();
				AdminDAO.Search_point_list(num);
				
				UI.delay_loop();
				loop = false;
				
				
			}
			else if(sel.equals("0")) {
				
				//0. 이전화면으로 돌아가기
				System.out.println();
				System.out.println("❗️이전화면❗️");
				loop = false;
				
			}
			
		}
		
	}
	
	public static void manage_review() {
		
		// 리뷰 관리
		
		boolean loop = true;
		Scanner scan = new Scanner(System.in);
		
		
		
		while (loop) {
			
			
			System.out.println(UI.alignCenter("1. 전체 리뷰 목록"));
			System.out.println(UI.alignCenter("2. 리뷰 검색"));
			System.out.println("");
			System.out.println(UI.alignCenter("0. 이전 화면"));
			
			
			//사용자 입력
			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				
				//1. 전체 리뷰 목록
				AdminDAO.Review_list();
				
				int num = admin_business_loop();
				if(num==1) {
					AdminDAO.Update_review();
				}
				else if(num==2) {
					AdminDAO.admin_Review_delete();
				}
						
			} else if(sel.equals("2")) {
				
				//2. 리뷰 검색
				System.out.print("🏝️ 검색을 원하시는 유저 번호를 입력하세요 : ");
				int num = scan.nextInt();
				AdminDAO.Search_review_list(num);
				UI.delay_loop();
				loop = false;
				
							
			}
			else if(sel.equals("0")) {
				
				//0. 이전화면으로 돌아가기
				System.out.println();
				System.out.println("❗️이전화면❗️");
				loop = false;
				
			}
			
		}
		
	}
	
	public static void manage_notice() {
		
		// 공지사항 관리
		
		boolean loop = true;
		Scanner scan = new Scanner(System.in);
		
		
		
		while (loop) {
			
			
			System.out.println(UI.alignCenter("1. 전체 공지사항 조회"));
			System.out.println(UI.alignCenter("2. 새 공지사항 등록"));
			System.out.println("");
			System.out.println(UI.alignCenter("0. 이전 화면"));
			
			
			//사용자 입력
			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				
				//1. 전체 공지사항 조회
				AdminDAO.Notice_list();
				
				int num = admin_business_loop();
				if(num==1) {
					AdminDAO.Update_notice();
				}
				else if(num==2) {
					AdminDAO.admin_Notice_delete();
				}
						
			} else if(sel.equals("2")) {
				
				//2. 새 공지사항 등록
				AdminDAO.Insert_notice();
				
			} 
			else if(sel.equals("0")) {
				
				//0. 이전화면으로 돌아가기
				System.out.println();
				System.out.println("❗️이전화면❗️");
				loop = false;
				
			}
			
		}
		
	}
	
	public static void manage_qna() {
		
		// 문의사항 관리
		
		boolean loop = true;
		Scanner scan = new Scanner(System.in);
		
		
		
		while (loop) {
			
			System.out.println(UI.alignCenter("1. 전체 문의사항"));
			System.out.println(UI.alignCenter("2. 문의사항 종류별 보기"));
			System.out.println(UI.alignCenter("3. 문의사항 검색"));
			System.out.println(UI.alignCenter("4. 문의처리 검색"));
			System.out.println("");
			System.out.println(UI.alignCenter("0. 이전 화면"));
			
			
			//사용자 입력
			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				
				//1. 전체 문의사항
				AdminDAO.Question_list();
				
				int num = admin_business_loop();
				if(num==1) {
					AdminDAO.Update_question();
				}
				else if(num==2) {
					AdminDAO.admin_Question_delete();
				}
						
			} else if(sel.equals("2")) {
				
				//2. 문의사항 종류별 보기
				System.out.print("🏝️ 검색을 원하시는 문의 종류 번호를 입력하세요 : ");
				int num = scan.nextInt();
				AdminDAO.Search_question_kind_list(num);
				UI.delay_loop();
				loop = false;
				
							
			} else if(sel.equals("3")) {
				
				//3. 문의사항 검색
				System.out.print("🏝️ 검색을 원하시는 유저 번호를 입력하세요 : ");
				int num = scan.nextInt();
				AdminDAO.Search_question_list(num);
				UI.delay_loop();
				loop = false;
							
			} else if(sel.equals("4")) {
				
				//4. 문의처리 검색
				System.out.print("🏝️ 검색을 원하시는 문의처리 번호를 입력하세요 : ");
				int num = scan.nextInt();
				AdminDAO.Search_question_reply_list(num);
				
				int num1 = admin_business_loop();
				if(num1==1) {
					AdminDAO.Update_question_reply();
				}
				else if(num1==2) {
					
				}
							
			} 
			else if(sel.equals("0")) {
				
				//0. 이전화면으로 돌아가기
				System.out.println();
				System.out.println("❗️이전화면❗️");
				loop = false;
				
			}
			
		}
		
	}
	
	public static void manage_account() {
		
		// 계정 관리
		
		boolean loop = true;
		Scanner scan = new Scanner(System.in);
		
		
		
		while (loop) {
			
			//사업장 관리 UI
			System.out.println(UI.alignCenter("1. 사업자 계정 관리"));
			System.out.println(UI.alignCenter("2. 유저 계정 관리"));
			System.out.println("");
			System.out.println(UI.alignCenter("3. 사업자 계정 검색"));
			System.out.println(UI.alignCenter("4. 유저 계정 검색"));
			System.out.println("");
			System.out.println(UI.alignCenter("0. 이전 화면"));
			
			
			//사용자 입력
			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				
				//1. 사업자 계정 관리
				AdminDAO.Owner_list();
				
				int num = admin_business_loop();
				if(num==1) {
					AdminDAO.Update_Owner();
				}
				else if(num==2) {
					AdminDAO.admin_Owner_delete();
				}
						
			} else if(sel.equals("2")) {
				
				//2. 유저 계정 관리
				AdminDAO.Users_list();
				
				int num = admin_business_loop();
				if(num==1) {
					AdminDAO.Update_Users();
				}
				else if(num==2) {
					AdminDAO.admin_Users_delete();
				}
				
							
			} else if(sel.equals("3")) {
				
				//3. 사업자 계정 검색
				System.out.print("🏝️ 검색을 원하시는 사업자 번호를 입력하세요 : ");
				int num = scan.nextInt();
				AdminDAO.Search_owner_list(num);
				UI.delay_loop();
				loop = false;
							
			} else if(sel.equals("4")) {
				
				//4. 유저 계정 검색
				System.out.print("🏝️ 검색을 원하시는 유저 번호를 입력하세요 : ");
				int num = scan.nextInt();
				AdminDAO.Search_users_list(num);
				UI.delay_loop();
				loop = false;
			}
			else if(sel.equals("0")) {
				
				//0. 이전화면으로 돌아가기
				System.out.println();
				System.out.println("❗️이전화면❗️");
				loop = false;
				
			}
			
		}
	}
			
	
}
