#include <iostream>
#include <fstream>
#include <vector>
#include <utility>
#include <string>

class Matrix {
	int w;
	int h;
	std::vector<int> arr;
public:
	Matrix(int x, int y);
	 ~Matrix() 
	 {
		 std::cout << "delete" << std::endl;
	 }
	int y() const { return h; }
	int x() const { return w; }
	int get(int x, int y) const;
	void set(int x, int y, int val);
	void deleteRow(int n);
	void deleteRow2(int num);
	void deleteColumn(int n);
	void debugDump(std::ostream& f) const
	{
		for (int n : arr)
		{
			f << n << " ";
		}
		f << std::endl;
		f << arr.capacity() << std::endl;
	}
};

Matrix::Matrix(int _x, int _y) : w(_x), h(_y), arr(w*h)
{};

int Matrix::get(int x, int y) const 
{
	return arr[w*y + x];
}

void Matrix::set(int x, int y, int val) 
{
	arr[w*y + x] = val;
}

void Matrix::deleteColumn(int num)
{
	if (num < 0) 
	{
		return;
	}
 	int count = 0;
	for (int k = 0; k < w*h - h; ++k) 
	{
		if ((k+count)%w == num) 
		{
			++count;
		}
		arr[k] = arr[k + count];
	}
	--w;
	arr.resize(w*h);
}

void Matrix::deleteRow(int num)
{
	if (num < 0) 
	{
		return;
	}
	for (int k = num*w; k < w*h - w; ++k) 
	{
		arr[k] = arr[k + w];
	}
	--h;
	arr.resize(w*h);
}


void Matrix::deleteRow2(int num)
{
	if (num < 0) 
	{
		return;
	}
	for (int i = 0; i < w; ++i)
	{
		for (int j = num; j < h - 1; ++j)
		{
			set(i, j, get(i, j + 1));
		}
	}
	--h;
	arr.resize(w*h);
}


std::pair<int, int> findAverege(const Matrix& mat)
{
	long summ = 0;
	for (int i = 0; i < mat.y(); i++)
	{
		for (int j = 0; j < mat.x(); j++)
		{
			summ += mat.get(j, i);
		}
	}

	for (int i = 0; i < mat.y(); i++)
	{
		for (int j = 0; j < mat.x(); j++)
		{
			if (mat.get(j, i) * mat.x() * mat.y() == summ) 
			{
				return std::make_pair(j, i);
			}
		}
	}

	return std::make_pair(-1, -1);
}


std::pair<int, int> findAveregeInRow(const Matrix& mat)
{
	for (int i = 0; i < mat.y(); i++)
	{
		long summ = 0;
		for (int j = 0; j < mat.x(); j++)
		{
			summ += mat.get(j, i);
		}

		for (int j = 0; j < mat.x(); j++)
		{
			if (mat.get(j, i) * mat.x() == summ)
			{
				return std::make_pair(j, i);
			}
		}
	}

	return std::make_pair(-1, -1);
}

void writeToOstream(std::ostream& f, const Matrix& mat)
{
	for (int i = 0; i < mat.y(); i++)
	{
		for (int j = 0; j < mat.x(); j++)
		{
			f << mat.get(j, i);
			f << " ";
		}
		f << std::endl;
	}
}

void writeToFile(const std::string& filename, const Matrix& mat)
{
	std::ofstream f(filename);
	writeToOstream(f, mat);
}

Matrix readFromIstream(std::istream &f)
{
	int y, x;
	f >> y;
	f >> x;
	Matrix mat(x, y);
	for (int i = 0; i < y; i++)
	{
		for (int j = 0; j < x; j++)
		{
			int val;
			f >> val;
			mat.set(j, i, val);
		}
	}
	return mat;
}

Matrix readFromFile(const std::string& filename)
{
	std::ifstream f(filename);
	if (!f) 
	{
		throw std::invalid_argument("no such file " + filename);
	}
	return readFromIstream(f);
}

#include <iostream>

int main()
{
	std::cout << "Enter a positive integer: ";
	int length;
	std::cin >> length;

	int *array = new int[length]; // use array new.  Note that length does not need to be constant!

	std::cout << "I just allocated an array of integers of length " << length << '\n';

	array[0] = 5; // set element 0 to value 5

	delete[] array; // use array delete to deallocate array

					// we don't need to set array to nullptr/0 here because it's going to go out of scope immediately after this anyway

	return 0;
}


int main1(int argc, char* argv[]) {
	try 
	{
		Matrix mat = readFromFile("data.dat");
		auto[x, y] = findAverege(mat);
		mat.deleteRow(y);
		writeToFile("data.res", mat);
		return 0;
	}
	catch (const std::exception& ex) 
	{
		std::cerr << ex.what() << std::endl;
		return 1;
	}
}
