import styled from "styled-components";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const PageContainer = styled.div`
  display: flex;
  width: 400px;
  justify-content: left;
  > div {
    width: 10px;
    margin: 0 10px;
  }
`;

const PageButton = styled.button`
  width: 26.8px;
  height: 27px;
  border: 1px solid var(--graylight);
  border-radius: var(--bd-rd);
  background-color: var(--white);
  margin: 0 2px;
  padding: 0 auto;
  :hover {
    cursor: pointer;
    background-color: var(--graylight);
  }
`;

const PageSelectedButton = styled.button`
  width: 26.8px;
  height: 27px;
  border: 1px solid var(--graylight);
  border-radius: var(--bd-rd);
  background-color: var(--orangelight);
  color: var(--white);
  margin: 0 2px;
  padding: 0 auto;
`;

const MovePageButton = styled.button`
  height: 27px;
  border: 1px solid var(--graylight);
  border-radius: 4px;
  background-color: var(--white);
  margin: 0 2px;
  :hover {
    cursor: pointer;
    background-color: var(--graylight);
  }
`;

function Pagination({ pageInfo, readPage, page }) {
  const [pageBtnClicked, setPageBtnClicked] = useState("");
  const navigate = useNavigate();
  const pageHandler = (pageNum) => {
    setPageBtnClicked(pageNum);
    readPage(pageNum);
    navigate(`/${pageNum}`);
  };

  const prevPageHandler = (pageNum) => {
    setPageBtnClicked(pageNum - 1);
    readPage(pageNum - 1);
    navigate(`/${pageNum - 1}`);
  };

  const nextPageHandler = (pageNum) => {
    setPageBtnClicked(pageNum + 1);
    readPage(pageNum + 1);
    navigate(`/${pageNum + 1}`);
  };

  const pageArray = [];
  if (pageInfo) {
    for (let i = 1; i <= pageInfo.totalPages; i++) {
      pageArray.push(i);
    }
  }

  return (
    <PageContainer>
      {pageBtnClicked === 1 || Number(page) === 1 ? (
        <span></span>
      ) : (
        <MovePageButton onClick={() => prevPageHandler(pageInfo.page)}>
          prev
        </MovePageButton>
      )}
      {/* 전체 페이지가 6미만일때 */}
      {pageArray.length > 0 &&
        pageArray.length <= 6 &&
        pageArray.map((pageNum) => {
          return page === pageNum.toString() ? (
            <PageSelectedButton key={pageNum}>{pageNum}</PageSelectedButton>
          ) : (
            <PageButton key={pageNum} onClick={() => pageHandler(pageNum)}>
              {pageNum}
            </PageButton>
          );
        })}
      {/* 전체 페이지가 7이거나 8일때 */}
      {(pageArray.length === 7 || pageArray.length === 8) &&
        Number(page) <= 4 &&
        pageArray.slice(0, 5).map((pageNum) => {
          return page === pageNum.toString() ? (
            <PageSelectedButton key={pageNum}>{pageNum}</PageSelectedButton>
          ) : (
            <PageButton key={pageNum} onClick={() => pageHandler(pageNum)}>
              {pageNum}
            </PageButton>
          );
        })}
      {(pageArray.length === 7 || pageArray.length === 8) &&
        Number(page) <= 4 && (
          <>
            <div>...</div>
            <PageButton
              key={pageArray.length}
              onClick={() => pageHandler(pageArray.length)}
            >
              {pageArray.length}
            </PageButton>
          </>
        )}
      {(pageArray.length === 7 || pageArray.length === 8) &&
        Number(page) >= 5 && (
          <>
            <PageButton key={1} onClick={() => pageHandler(1)}>
              1
            </PageButton>
            <div>...</div>
          </>
        )}
      {(pageArray.length === 7 || pageArray.length === 8) &&
        Number(page) >= 5 &&
        pageArray.slice(pageArray.length - 5).map((pageNum) => {
          return page === pageNum.toString() ? (
            <PageSelectedButton key={pageNum}>{pageNum}</PageSelectedButton>
          ) : (
            <PageButton key={pageNum} onClick={() => pageHandler(pageNum)}>
              {pageNum}
            </PageButton>
          );
        })}
      {/* 전체 페이지가 9이상일 때 */}
      {pageArray.length >= 9 &&
        Number(page) <= 4 &&
        pageArray.slice(0, 5).map((pageNum) => {
          return page === pageNum.toString() ? (
            <PageSelectedButton key={pageNum}>{pageNum}</PageSelectedButton>
          ) : (
            <PageButton key={pageNum} onClick={() => pageHandler(pageNum)}>
              {pageNum}
            </PageButton>
          );
        })}
      {pageArray.length >= 9 && Number(page) <= 4 && (
        <>
          <div>...</div>
          <PageButton
            key={pageArray.length}
            onClick={() => pageHandler(pageArray.length)}
          >
            {pageArray.length}
          </PageButton>
        </>
      )}
      {pageArray.length >= 9 &&
        Number(page) >= 5 &&
        Number(page) <= pageArray.length - 4 && (
          <>
            <PageButton key={1} onClick={() => pageHandler(1)}>
              1
            </PageButton>
            <div>...</div>
          </>
        )}
      {pageArray.length >= 9 &&
        Number(page) >= 5 &&
        Number(page) <= pageArray.length - 4 &&
        pageArray.slice(Number(page) - 3, Number(page) + 2).map((pageNum) => {
          return page === pageNum.toString() ? (
            <PageSelectedButton key={pageNum}>{pageNum}</PageSelectedButton>
          ) : (
            <PageButton key={pageNum} onClick={() => pageHandler(pageNum)}>
              {pageNum}
            </PageButton>
          );
        })}
      {pageArray.length >= 9 &&
        Number(page) >= 5 &&
        Number(page) <= pageArray.length - 4 && (
          <>
            <div>...</div>
            <PageButton
              key={pageArray.length}
              onClick={() => pageHandler(pageArray.length)}
            >
              {pageArray.length}
            </PageButton>
          </>
        )}
      {pageArray.length >= 9 &&
        Number(page) >= pageArray.length - 3 &&
        Number(page) <= pageArray.length && (
          <>
            <PageButton key={1} onClick={() => pageHandler(1)}>
              1
            </PageButton>
            <div>...</div>
          </>
        )}
      {pageArray.length >= 9 &&
        Number(page) >= pageArray.length - 3 &&
        Number(page) <= pageArray.length &&
        pageArray.slice(pageArray.length - 5).map((pageNum) => {
          return page === pageNum.toString() ? (
            <PageSelectedButton key={pageNum}>{pageNum}</PageSelectedButton>
          ) : (
            <PageButton key={pageNum} onClick={() => pageHandler(pageNum)}>
              {pageNum}
            </PageButton>
          );
        })}
      {pageArray.length !== 0 &&
      (pageBtnClicked === pageArray.length ||
        Number(page) === pageArray.length) ? (
        <span></span>
      ) : (
        <MovePageButton onClick={() => nextPageHandler(pageInfo.page)}>
          next
        </MovePageButton>
      )}
    </PageContainer>
  );
}

export default Pagination;
