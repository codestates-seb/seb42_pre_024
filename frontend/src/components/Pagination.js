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
      {pageArray.length === 7 &&
        (pageBtnClicked <= 5 || Number(page) <= 5) &&
        pageArray.slice(0, 5).map((pageNum) => {
          return page === pageNum.toString() ? (
            <PageSelectedButton key={pageNum}>{pageNum}</PageSelectedButton>
          ) : (
            <PageButton key={pageNum} onClick={() => pageHandler(pageNum)}>
              {pageNum}
            </PageButton>
          );
        })}
      {pageArray.length === 7 && (pageBtnClicked <= 5 || Number(page) <= 5) && (
        <>
          <div>...</div>
          <PageButton key={7} onClick={() => pageHandler(7)}>
            7
          </PageButton>
        </>
      )}
      {pageArray.length === 7 && (pageBtnClicked >= 6 || Number(page) >= 6) && (
        <>
          <PageButton key={1} onClick={() => pageHandler(1)}>
            1
          </PageButton>
          <div>...</div>
        </>
      )}
      {pageArray.length === 7 &&
        (pageBtnClicked >= 6 || Number(page) >= 6) &&
        pageArray.slice(2).map((pageNum) => {
          return page === pageNum.toString() ? (
            <PageSelectedButton key={pageNum}>{pageNum}</PageSelectedButton>
          ) : (
            <PageButton key={pageNum} onClick={() => pageHandler(pageNum)}>
              {pageNum}
            </PageButton>
          );
        })}
      {pageArray.length >= 8 &&
        (pageBtnClicked <= 4 || Number(page) <= 4) &&
        pageArray.slice(0, 5).map((pageNum) => {
          return page === pageNum.toString() ? (
            <PageSelectedButton key={pageNum}>{pageNum}</PageSelectedButton>
          ) : (
            <PageButton key={pageNum} onClick={() => pageHandler(pageNum)}>
              {pageNum}
            </PageButton>
          );
        })}
      {pageArray.length >= 8 && (pageBtnClicked <= 4 || Number(page) <= 4) && (
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
      {pageArray.length >= 8 &&
        (pageBtnClicked >= 5 || Number(page) >= 5) &&
        (pageBtnClicked <= pageArray.length - 4 ||
          Number(page) <= pageArray.length - 4) && (
          <>
            <PageButton key={1} onClick={() => pageHandler(1)}>
              1
            </PageButton>
            <div>...</div>
          </>
        )}
      {pageArray.length >= 8 &&
        (pageBtnClicked >= 5 || Number(page) >= 5) &&
        (pageBtnClicked <= pageArray.length - 4 ||
          Number(page) <= pageArray.length - 4) &&
        pageArray
          .slice(pageBtnClicked - 3, pageBtnClicked + 2)
          .map((pageNum) => {
            return page === pageNum.toString() ? (
              <PageSelectedButton key={pageNum}>{pageNum}</PageSelectedButton>
            ) : (
              <PageButton key={pageNum} onClick={() => pageHandler(pageNum)}>
                {pageNum}
              </PageButton>
            );
          })}
      {pageArray.length >= 8 &&
        (pageBtnClicked >= 5 || Number(page) >= 5) &&
        (pageBtnClicked <= pageArray.length - 4 ||
          Number(page) <= pageArray.length - 4) && (
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
      {pageArray.length >= 8 &&
        (pageBtnClicked >= pageArray.length - 3 ||
          Number(page) >= pageArray.length - 3) &&
        (pageBtnClicked <= pageArray.length ||
          Number(page) <= pageArray.length) && (
          <>
            <PageButton key={1} onClick={() => pageHandler(1)}>
              1
            </PageButton>
            <div>...</div>
          </>
        )}
      {pageArray.length >= 8 &&
        (pageBtnClicked >= pageArray.length - 3 ||
          Number(page) >= pageArray.length - 3) &&
        (pageBtnClicked <= pageArray.length ||
          Number(page) <= pageArray.length) &&
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
